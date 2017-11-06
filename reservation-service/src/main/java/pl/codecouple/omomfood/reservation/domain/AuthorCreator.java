package pl.codecouple.omomfood.reservation.domain;

import pl.codecouple.omomfood.reservation.dto.CreateReservationDTO;
import pl.codecouple.omomfood.reservation.dto.GetAuthorDTO;

/**
 * Created by CodeCouple.pl
 */
class AuthorCreator {

    GetAuthorDTO from(CreateReservationDTO createReservationDTO){
        return GetAuthorDTO.builder()
                .authorID(createReservationDTO.getAuthorID())
                .build();
    }

}
