package pl.codecouple.omomfood.user.domain;

import org.springframework.data.repository.Repository;
import pl.codecouple.omomfood.user.exceptions.UserNotFound;

import java.util.List;

/**
 * Created by CodeCouple.pl
 */
interface UserRepository extends Repository<User, Long> {

    List<User> findAll();

    User save(User reservation);

    User findById(long id);

    void deleteById(long id);

    default User findByIdOrThrow(long id) {
        User user = findById(id);
        if (user == null) {
            throw new UserNotFound();
        }
        return user;
    }
}
