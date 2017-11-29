package pl.codecouple.omomfood.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by CodeCouple.pl
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfferDTO {

    private long offerID;
    private long quantity;
    private List<Integer> assignedUsers;

}
