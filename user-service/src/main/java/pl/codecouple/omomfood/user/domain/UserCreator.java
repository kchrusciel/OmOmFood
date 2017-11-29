package pl.codecouple.omomfood.user.domain;

import pl.codecouple.omomfood.user.dto.CreateUserDTO;

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

}
