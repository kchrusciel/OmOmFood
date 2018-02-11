package pl.codecouple.omomfood.user.domain;

import pl.codecouple.omomfood.user.dto.CreateUserDTO;
import pl.codecouple.omomfood.user.dto.UserDTO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by CodeCouple.pl
 */
public class UserFacade {

    private final UserRepository userRepository;
    private final UserCreator userCreator;

    public UserFacade(UserRepository userRepository, UserCreator userCreator) {
        this.userRepository = userRepository;
        this.userCreator = userCreator;
    }

    public UserDTO getUserById(long id) {
        User userFromDb = userRepository.findByIdOrThrow(id);
        return userFromDb.dto();
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(User::dto)
                .collect(Collectors.toList());
    }

    public UserDTO createUser(CreateUserDTO userToCreate) {
        return userRepository.save(userCreator.from(userToCreate)).dto();
    }

    public void delete(long id) {
        userRepository.findByIdOrThrow(id);
        userRepository.deleteById(id);
    }

    public UserDTO update(UserDTO userToUpdate) {
        userRepository.findByIdOrThrow(userToUpdate.getId());
        return userRepository.save(userCreator.from(userToUpdate)).dto();
    }

    public UserDTO updateFields(long id, Map<String, Object> fieldsToUpdate) {
        User user = userRepository.findByIdOrThrow(id);
        if (fieldsToUpdate.containsKey("name")) {
            user.setName(String.valueOf(fieldsToUpdate.get("name")));
        } else if (fieldsToUpdate.containsKey("surname")) {
            user.setSurname(String.valueOf(fieldsToUpdate.get("surname")));
        }
        return userRepository.save(user).dto();
    }
}
