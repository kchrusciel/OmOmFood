package pl.codecouple.omomfood.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.codecouple.omomfood.user.domain.UserFacade;
import pl.codecouple.omomfood.user.dto.UserDTO;

/**
 * Created by CodeCouple.pl
 */
@RestController
@RequestMapping("/users")
class UserController {

    private final UserFacade facade;

    UserController(UserFacade facade) {
        this.facade = facade;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    UserDTO getUserByID(@PathVariable("id") long id) {
        return facade.getUserById(id);
    }

}
