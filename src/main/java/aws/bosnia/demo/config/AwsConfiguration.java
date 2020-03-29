package aws.bosnia.demo.config;

import aws.bosnia.demo.Book;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.BillingMode;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.Credentials;
import com.amazonaws.services.securitytoken.model.GetSessionTokenRequest;
import com.amazonaws.services.securitytoken.model.GetSessionTokenResult;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableDynamoDBRepositories(basePackages = "aws.bosnia.demo")
@Profile("aws")
public class AwsConfiguration {

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
//        AWSSecurityTokenService sts_client = AWSSecurityTokenServiceClientBuilder.standard()
//                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("https://sts.eu-central-1.amazonaws.com", "eu-central-1"))
//                .build();
//
//        GetSessionTokenRequest session_token_request = new GetSessionTokenRequest();
//        session_token_request.setDurationSeconds(7200);
//
//        GetSessionTokenResult session_token_result =
//                sts_client.getSessionToken(session_token_request);
//
//        Credentials session_creds = session_token_result.getCredentials();
//
//        BasicSessionCredentials sessionCredentials = new BasicSessionCredentials(
//                session_creds.getAccessKeyId(),
//                session_creds.getSecretAccessKey(),
//                session_creds.getSessionToken());
//
        AmazonDynamoDB amazonDynamoDB
                = AmazonDynamoDBClientBuilder.standard()
//                .withCredentials(new AWSStaticCredentialsProvider(sessionCredentials))
                .withRegion(Regions.EU_CENTRAL_1)
                .build();

        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        CreateTableRequest tableRequest = dynamoDBMapper
                .generateCreateTableRequest(Book.class);
        tableRequest.setBillingMode(BillingMode.PAY_PER_REQUEST.toString());

        TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);
        return amazonDynamoDB;
    }

}
