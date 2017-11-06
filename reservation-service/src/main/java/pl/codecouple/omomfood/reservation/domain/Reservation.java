package pl.codecouple.omomfood.reservation.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.codecouple.omomfood.reservation.dto.ReservationDTO;

import javax.persistence.Entity;

/**
 * Created by CodeCouple.pl
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
class Reservation {

    private long reservationID;
    private long offerID;
    private long authorID;
    private long quantity;

    ReservationDTO dto() {
        return ReservationDTO.builder()
                .reservationID(reservationID)
                .offerID(offerID)
                .authorID(authorID)
                .quantity(quantity)
                .build();
    }

}
