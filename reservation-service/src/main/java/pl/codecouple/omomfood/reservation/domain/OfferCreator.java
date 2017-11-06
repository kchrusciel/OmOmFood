package pl.codecouple.omomfood.reservation.domain;

import pl.codecouple.omomfood.reservation.dto.CreateReservationDTO;
import pl.codecouple.omomfood.reservation.dto.GetOfferDTO;

/**
 * Created by CodeCouple.pl
 */
class OfferCreator {

    GetOfferDTO from(CreateReservationDTO createReservationDTO){
        return GetOfferDTO.builder()
                .offerID(createReservationDTO.getOfferID())
                .build();
    }

}
