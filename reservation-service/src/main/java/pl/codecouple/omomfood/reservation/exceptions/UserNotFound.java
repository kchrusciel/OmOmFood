package pl.codecouple.omomfood.reservation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by CodeCouple.pl
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User not found")
public class UserNotFound extends RuntimeException {

    public UserNotFound() {
        super("User not found");
    }
}
