package pl.codecouple.omomfood.reservation.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.codecouple.omomfood.infrastructure.jpa.Auditable;
import pl.codecouple.omomfood.reservation.dto.ReservationDTO;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

/**
 * Created by CodeCouple.pl
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
class Reservation extends Auditable {

    @Id
    @GeneratedValue
    private long id;
    private long offerID;
    private long authorID;
    @ElementCollection(targetClass = Integer.class)
    private List<Integer> assignedUsers;

    ReservationDTO dto() {
        return ReservationDTO.builder()
                .id(id)
                .offerID(offerID)
                .userID(authorID)
                .assignedUsers(assignedUsers)
                .build();
    }

}
