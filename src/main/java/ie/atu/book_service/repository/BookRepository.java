package ie.atu.book_service.repository;

import ie.atu.book_service.model.Book;
import jakarta.validation.ReportAsSingleViolation;
import org.hibernate.Internal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByBookID(Long bookID);

    Book getBookByBookID(Long bookID);
}
