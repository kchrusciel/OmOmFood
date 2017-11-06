package pl.codecouple.omomfood.reservation.domain;

import pl.codecouple.omomfood.reservation.dto.CreateReservationDTO;

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

}
