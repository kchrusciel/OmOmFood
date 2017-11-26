package pl.codecouple.omomfood.reservation.domain;

import pl.codecouple.omomfood.reservation.dto.CreateReservationDTO;
import pl.codecouple.omomfood.reservation.dto.GetUserDTO;

/**
 * Created by CodeCouple.pl
 */
class UserCreator {

    GetUserDTO from(CreateReservationDTO createReservationDTO){
        return GetUserDTO.builder()
                .userID(createReservationDTO.getUserID())
                .build();
    }

}
