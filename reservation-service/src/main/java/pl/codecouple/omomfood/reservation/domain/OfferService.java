package pl.codecouple.omomfood.reservation.domain;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.codecouple.omomfood.reservation.dto.GetOfferDTO;
import pl.codecouple.omomfood.reservation.dto.OfferDTO;
import pl.codecouple.omomfood.reservation.exceptions.OfferNotFound;

/**
 * Created by CodeCouple.pl
 */
@Service
class OfferService {

    private final OfferServiceGetter getter;

    OfferService(OfferServiceGetter getter) {
        this.getter = getter;
    }

    OfferDTO getOffer(GetOfferDTO getOfferDTO) {
        ResponseEntity<OfferDTO> offer = getter.getOffer(getOfferDTO);
        if (offer == null || offer.getStatusCode() != HttpStatus.OK) {
            throw new OfferNotFound();
        }
        return offer.getBody();
    }

    boolean isOfferAvailable(GetOfferDTO getOfferDTO) {
        ResponseEntity<OfferDTO> offer = getter.getOffer(getOfferDTO);
        if (offer == null || offer.getStatusCode() != HttpStatus.OK) {
            throw new OfferNotFound();
        }
        return true;
    }


}
