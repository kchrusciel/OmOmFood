package pl.codecouple.omomfood.user.domain;

import pl.codecouple.omomfood.user.dto.CreateUserDTO;
import pl.codecouple.omomfood.user.dto.UserDTO;

import static java.util.Objects.requireNonNull;

/**
 * Created by CodeCouple.pl
 */
class UserCreator {

    User from(CreateUserDTO createUserDTO) {
        requireNonNull(createUserDTO);
        return User.builder()
                .username(createUserDTO.getUsername())
                .surname(createUserDTO.getSurname())
                .name(createUserDTO.getName())
                .build();
    }

    User from(UserDTO userDTO) {
        requireNonNull(userDTO);
        return User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .build();
    }
}
