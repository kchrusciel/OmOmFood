package pl.codecouple.omomfood.user.domain;

import pl.codecouple.omomfood.user.dto.CreateUserDTO;
import pl.codecouple.omomfood.user.dto.UserDTO;

import java.util.List;
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
        return userRepository.findById(id).dto();
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(User::dto)
                .collect(Collectors.toList());
    }

    public UserDTO save(CreateUserDTO createUserDTO) {
        return userRepository.save(userCreator.from(createUserDTO)).dto();
    }

}
