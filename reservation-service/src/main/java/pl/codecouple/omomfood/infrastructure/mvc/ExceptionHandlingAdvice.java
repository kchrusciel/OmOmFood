package pl.codecouple.omomfood.infrastructure.mvc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by CodeCouple.pl
 */
@ControllerAdvice
class ExceptionHandlingAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ErrorMessage> validationHandler(MethodArgumentNotValidException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @Getter
    @AllArgsConstructor
    class ErrorMessage {
        private String message;
        private String details;

        ErrorMessage(String message) {
            this.message = message;
        }
    }

}
