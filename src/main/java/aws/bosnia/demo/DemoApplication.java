package aws.bosnia.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.util.UUID;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Resource
    private DemosRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Demo demo = new Demo();
        repository.save(demo);
        repository.findAll().forEach(System.out::println);
    }
}
