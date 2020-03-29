package aws.bosnia.demo.config;

import aws.bosnia.demo.Book;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.BillingMode;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
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

        AmazonDynamoDB amazonDynamoDB
                = AmazonDynamoDBClientBuilder.standard()
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
