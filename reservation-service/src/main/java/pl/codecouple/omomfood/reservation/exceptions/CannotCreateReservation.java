package pl.codecouple.omomfood.reservation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by CodeCouple.pl
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Cannot create a reservation")
public class CannotCreateReservation extends RuntimeException {

    public CannotCreateReservation() {
        super("Cannot create a reservation");
    }
}
