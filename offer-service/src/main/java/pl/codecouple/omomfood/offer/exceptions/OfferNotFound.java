package pl.codecouple.omomfood.offer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by CodeCouple.pl
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Offer not found")
public class OfferNotFound extends RuntimeException{
    public OfferNotFound() {
        super("Offer not found");
    }
}
