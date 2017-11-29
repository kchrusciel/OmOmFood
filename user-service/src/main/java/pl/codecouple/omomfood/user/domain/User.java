package pl.codecouple.omomfood.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.codecouple.omomfood.infrastructure.jpa.Auditable;
import pl.codecouple.omomfood.user.dto.UserDTO;

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
class User extends Auditable {

    @Id
    @GeneratedValue
    private long id;

    private String username;
    private String name;
    private String surname;

    UserDTO dto() {
        return UserDTO.builder()
                .id(id)
                .username(username)
                .name(name)
                .surname(surname)
                .build();
    }

}
