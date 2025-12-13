package ie.atu.book_service.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.NumberFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "book")
@Entity
public class Book {

    @NotBlank(message = "Name must not be blank")
    @Size(min = 1, max = 100, message ="Invalid Entry")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name can contain only English letters")
    @Schema(example = "Harry Potter")
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long bookID;

    @NotBlank(message = "Author must not be blank")
    @Size(min = 1, max = 100, message ="Invalid Entry")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Author name can contain only English letters")
    @Schema(example = "JK Rowling")
    private String author;

    @NotBlank(message = "Publisher must not be blank")
    @Size(min = 1, max = 100, message ="Invalid Entry")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Publisher name can contain only English letters")
    @Schema(example = "Bloomsburry")
    private String publisher;
}
