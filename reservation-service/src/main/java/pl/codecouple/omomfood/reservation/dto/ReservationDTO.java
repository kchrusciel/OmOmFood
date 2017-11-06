package pl.codecouple.omomfood.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by CodeCouple.pl
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDTO {

    private long reservationID;
    private long offerID;
    private long authorID;
    private long quantity;

}
