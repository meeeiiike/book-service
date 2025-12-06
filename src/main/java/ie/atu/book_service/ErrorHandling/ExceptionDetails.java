package ie.atu.book_service.ErrorHandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDetails {

    private String fieldName;
    private String fieldValue;
}

