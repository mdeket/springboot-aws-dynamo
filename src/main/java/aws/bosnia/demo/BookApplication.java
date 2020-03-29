package aws.bosnia.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class BookApplication implements CommandLineRunner {

    @Resource
    private BookRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Book demo = new Book("Problem spinoza");
        repository.save(demo);
        repository.findAll().forEach(System.out::println);
    }
}
