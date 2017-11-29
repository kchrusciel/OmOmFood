package pl.codecouple.omomfood.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * Created by CodeCouple.pl
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDTO {

    @Min(1)
    private long id;
    @Min(1)
    private long offerID;
    @Min(1)
    private long userID;

    private List<Integer> assignedUsers;

}
