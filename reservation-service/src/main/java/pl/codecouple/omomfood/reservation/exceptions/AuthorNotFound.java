package pl.codecouple.omomfood.reservation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by CodeCouple.pl
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Author not found")
public class AuthorNotFound extends RuntimeException {

    public AuthorNotFound() {
        super("Author not found");
    }
}
