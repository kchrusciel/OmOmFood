package pl.codecouple.omomfood.reservation.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.codecouple.omomfood.reservation.dto.ReservationDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by CodeCouple.pl
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
class Reservation {

    @Id
    @GeneratedValue
    private long id;
    private long offerID;
    private long authorID;
    private long quantity;

    ReservationDTO dto() {
        return ReservationDTO.builder()
                .id(id)
                .offerID(offerID)
                .authorID(authorID)
                .quantity(quantity)
                .build();
    }

}
