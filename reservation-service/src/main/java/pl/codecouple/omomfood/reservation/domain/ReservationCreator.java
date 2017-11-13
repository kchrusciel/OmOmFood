package pl.codecouple.omomfood.reservation.domain;

import pl.codecouple.omomfood.reservation.dto.CreateReservationDTO;
import pl.codecouple.omomfood.reservation.dto.ReservationDTO;

import static java.util.Objects.requireNonNull;

/**
 * Created by CodeCouple.pl
 */
class ReservationCreator {

    Reservation from(CreateReservationDTO createReservationDTO) {
        requireNonNull(createReservationDTO);
        return Reservation.builder()
                .authorID(createReservationDTO.getAuthorID())
                .offerID(createReservationDTO.getOfferID())
                .quantity(createReservationDTO.getQuantity())
                .build();
    }

    Reservation from(ReservationDTO reservation) {
        requireNonNull(reservation);
        return Reservation.builder()
                .id(reservation.getId())
                .authorID(reservation.getAuthorID())
                .offerID(reservation.getOfferID())
                .quantity(reservation.getQuantity())
                .build();
    }

}
