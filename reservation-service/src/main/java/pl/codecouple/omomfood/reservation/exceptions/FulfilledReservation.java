package pl.codecouple.omomfood.reservation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by CodeCouple.pl
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Reservation is fulfilled")
public class FulfilledReservation extends RuntimeException {

    public FulfilledReservation(String message) {
        super(message);
    }
}
