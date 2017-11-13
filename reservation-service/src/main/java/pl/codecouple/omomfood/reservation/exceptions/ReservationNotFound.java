package pl.codecouple.omomfood.reservation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by CodeCouple.pl
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Reservation not found")
public class ReservationNotFound extends RuntimeException {

    public ReservationNotFound() {
        super("Reservation not found");
    }
}
