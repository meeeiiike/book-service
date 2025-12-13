package ie.atu.book_service.service;


import ie.atu.book_service.ErrorHandling.NotFoundException;
import ie.atu.book_service.model.Book;
import ie.atu.book_service.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {

        return bookRepository.findAll();

    }

    public Book findById(Long id) {

        return bookRepository.findByBookID(id)
                .orElseThrow(() -> new NotFoundException("Book Not Found"));

    }

    public Book create(Book book) {

        return bookRepository.save(book);


    }

    public void update(Book book) {

        Book existingBook = findById(book.getBookID());

        existingBook.setBookID(book.getBookID());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setName(book.getName());
        existingBook.setPublisher(book.getPublisher());


        bookRepository.save(existingBook);
    }

    public void delete(Long id) {

        Book deletedBook = findById(id);

        bookRepository.delete(deletedBook);
    }








}
