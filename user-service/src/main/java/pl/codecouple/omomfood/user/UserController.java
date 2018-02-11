package pl.codecouple.omomfood.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.codecouple.omomfood.user.domain.UserFacade;
import pl.codecouple.omomfood.user.dto.CreateUserDTO;
import pl.codecouple.omomfood.user.dto.UserDTO;

import java.util.List;
import java.util.Map;

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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<UserDTO> findAllUsers() {
        return facade.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserDTO createUser(@RequestBody CreateUserDTO userToCreate) {
        return facade.createUser(userToCreate);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    UserDTO update(@RequestBody UserDTO userToUpdate) {
        return facade.update(userToUpdate);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    UserDTO updateFields(@PathVariable("id") long id, @RequestBody Map<String, Object> fieldsToUpdate) {
        return facade.updateFields(id, fieldsToUpdate);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") long id) {
        facade.delete(id);
    }
}
