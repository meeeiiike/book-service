package ie.atu.book_service.controller;

import ie.atu.book_service.ErrorHandling.NotFoundException;
import ie.atu.book_service.model.Book;
import ie.atu.book_service.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

@RequestMapping("/book")
@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<Book>> getAllBooks() {

        return ResponseEntity.ok(bookService.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Long id) {

        return ResponseEntity.ok(bookService.findById(id));

    }

    @PostMapping("/createBook")
    public ResponseEntity<?> createBook(@Valid @RequestBody Book book) {

        Book created =  bookService.create(book);

        return ResponseEntity
                .created(URI.create("/api/books/" + created.getBookID()))
                .body("Book created with id " + created.getBookID());

    }

    @PutMapping("updateBook/{id}")
    public ResponseEntity<?> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody Book book) {

        book.setBookID(id);
        bookService.update(book);

        return ResponseEntity.ok("Book updated with id " + id);
    }


    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id) {

        bookService.delete(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body("Book deleted with id " + id);

    }


}
