package ie.atu.book_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ie.atu.book_service.ErrorHandling.NotFoundException;
import ie.atu.book_service.controller.BookController;
import ie.atu.book_service.model.Book;
import ie.atu.book_service.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @Test
    void getBookById_returns200() throws Exception {

        Book book = Book.builder()
                .bookID(1L)
                .name("Harry Potter")
                .author("JK Rowling")
                .publisher("Bloomsbury")
                .build();

        when(bookService.findById(1L)).thenReturn(book);

        mockMvc.perform(get("/book/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value("Harry Potter"));
    }

    @Test
    void getAllBooks_returns200() throws Exception {

        Book book = Book.builder()
                .bookID(1L)
                .name("Harry Potter")
                .author("JK Rowling")
                .publisher("Bloomsbury")
                .build();

        Book book2 = Book.builder()
                .bookID(2L)
                .name("Harry Potter Two")
                .author("JK Rowling")
                .publisher("Bloomsbury")
                .build();


        List<Book> books = List.of(book, book2);


        when(bookService.findAll()).thenReturn(books);

        mockMvc.perform(get("/book/getAllBooks"))
                .andExpect(status().isOk());

    }



    @Test
    void deleteBookById_returns200() throws Exception {

        mockMvc.perform(delete("/book/deleteBook/1"))
                .andExpect(status().isOk());
    }

    @Test
    void updateBookById_returns200() throws Exception {
        Book book = Book.builder()
                .bookID(1L)
                .name("Harry Potter")
                .author("JK Rowling")
                .publisher("Bloomsbury")
                .build();



    }


    @Test
    void createBook_returns201() throws Exception {

        Book requestBook = Book.builder()
                .name("Harry Potter")
                .author("JK Rowling")
                .publisher("Bloomsbury")
                .build();

        Book savedBook = Book.builder()
                .bookID(1L)
                .name("Harry Potter")
                .author("JK Rowling")
                .publisher("Bloomsbury")
                .build();

        when(bookService.create(any(Book.class))).thenReturn(savedBook);

        ObjectMapper mapper = new ObjectMapper();
        String jsonValue = mapper.writeValueAsString(requestBook);

        mockMvc.perform(post("/book/createBook")
                        .contentType("application/json")
                        .content(jsonValue))
                .andExpect(status().isCreated())
                .andExpect(content().string("Book created with id 1"));
    }


    @Test
    void updateBook_returns204() throws Exception {

        Book book = Book.builder()
                .bookID(1L)
                .name("Harry Potter")
                .author("JK Rowling")
                .publisher("Bloomsbury")
                .build();

        Book updatedBook = Book.builder()
                .bookID(1L)
                .name("Harry Potter Two")
                .author("JK Rowling")
                .publisher("Bloomsbury")
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String jsonValue = mapper.writeValueAsString(book);

        mockMvc.perform(put("/book/updateBook/1")
                .contentType("application/json")
                .content(jsonValue))
                .andExpect(status().isOk())
                .andExpect(content().string("Book updated with id 1"));

    }
}
