package pl.codecouple.omomfood.reservation.domain;

import pl.codecouple.omomfood.reservation.dto.CreateReservationDTO;
import pl.codecouple.omomfood.reservation.dto.ReservationDTO;

import java.util.Collections;

import static java.util.Objects.requireNonNull;

/**
 * Created by CodeCouple.pl
 */
class ReservationCreator {

    Reservation from(CreateReservationDTO createReservationDTO) {
        requireNonNull(createReservationDTO);
        return Reservation.builder()
                .authorID(createReservationDTO.getUserID())
                .offerID(createReservationDTO.getOfferID())
                .assignedUsers(Collections.emptyList())
                .build();
    }

    Reservation from(ReservationDTO reservation) {
        requireNonNull(reservation);
        return Reservation.builder()
                .id(reservation.getId())
                .authorID(reservation.getUserID())
                .offerID(reservation.getOfferID())
                .assignedUsers(reservation.getAssignedUsers())
                .build();
    }

}
