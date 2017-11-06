package pl.codecouple.omomfood.reservation.dto;

import lombok.*;

/**
 * Created by CodeCouple.pl
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateReservationDTO {

    private long offerID;
    private long authorID;
    private long quantity;

}
