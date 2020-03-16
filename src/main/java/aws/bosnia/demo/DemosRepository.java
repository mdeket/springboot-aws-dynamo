package aws.bosnia.demo;


import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
public interface DemosRepository extends CrudRepository<Demo, Long> {
}
