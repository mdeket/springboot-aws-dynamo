package aws.bosnia.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping
    public ResponseEntity addTvShow(@RequestBody Map<String, String> data) {
        String tvShowName = data.get("name");
        Book tvShow = new Book(tvShowName);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookRepository.save(tvShow));
    }
}
