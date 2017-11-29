package pl.codecouple.omomfood.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

/**
 * Created by CodeCouple.pl
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateReservationDTO {

    @Min(1)
    private long offerID;
    @Min(1)
    private long userID;

}
