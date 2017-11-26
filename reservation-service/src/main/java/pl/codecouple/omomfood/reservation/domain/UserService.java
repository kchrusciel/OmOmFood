package pl.codecouple.omomfood.reservation.domain;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.codecouple.omomfood.reservation.dto.GetUserDTO;
import pl.codecouple.omomfood.reservation.dto.UserDTO;
import pl.codecouple.omomfood.reservation.exceptions.UserNotFound;

/**
 * Created by CodeCouple.pl
 */
@Service
class UserService {

    private final UserServiceGetter getter;

    UserService(UserServiceGetter getter) {
        this.getter = getter;
    }

    UserDTO getUser(GetUserDTO getUserDTO) {
        ResponseEntity<UserDTO> author = getter.getUser(getUserDTO);
        if (author == null || author.getStatusCode() != HttpStatus.OK) {
            throw new UserNotFound();
        }
        return author.getBody();
    }

    boolean isUserAvailable(GetUserDTO getUserDTO) {
        ResponseEntity<UserDTO> offer = getter.getUser(getUserDTO);
        if (offer == null || offer.getStatusCode() != HttpStatus.OK) {
            throw new UserNotFound();
        }
        return true;
    }


}
