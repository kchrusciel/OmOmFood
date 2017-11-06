package pl.codecouple.omomfood.reservation.domain;

import org.springframework.stereotype.Service;
import pl.codecouple.omomfood.reservation.dto.GetOfferDTO;
import pl.codecouple.omomfood.reservation.exceptions.OfferNotFound;

/**
 * Created by CodeCouple.pl
 */
@Service
class OfferService {

    GetOfferDTO getOffer(GetOfferDTO getOfferDTO){
        throw new OfferNotFound();
    }

}
