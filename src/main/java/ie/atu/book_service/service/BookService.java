package ie.atu.book_service.service;


import ie.atu.book_service.ErrorHandling.DuplicateExceptionHandling;
import ie.atu.book_service.ErrorHandling.NotFoundException;
import ie.atu.book_service.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final List<Book> books = new ArrayList<>();

    public List<Book> findAll() {
        return new ArrayList<>(books);
    }

    public Optional<Book> findById(String id) {
        for (Book book : books) {
            if(book.getBookID().equals(id)) {
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }

    public Book create(Book book) {
        if(findById(book.getBookID()).isPresent()) {
            throw new DuplicateExceptionHandling("Book ID with " + book.getBookID() + " already exists");
        }else{
            books.add(book);
            return book;
        }
    }

    public void update(Book book) {
        if(findById(book.getBookID()).isPresent()) {
            books.set(books.indexOf(findById(book.getBookID()).get()), book);
        } else{
            throw new NotFoundException("Book ID doesn't exist");
        }
    }

    public void delete(String id) {
        if (findById(id).isPresent()) {
            books.remove(findById(id).get());
        } else{
            throw new NotFoundException("Book ID doesn't exist");
        }
    }

}