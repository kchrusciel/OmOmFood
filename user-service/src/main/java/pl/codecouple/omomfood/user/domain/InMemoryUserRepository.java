package pl.codecouple.omomfood.user.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

/**
 * Created by CodeCouple.pl
 */
class InMemoryUserRepository implements UserRepository {

    private ConcurrentHashMap<Long, User> map = new ConcurrentHashMap<>();
    private long counter;

    @Override
    public User save(User user) {
        requireNonNull(user);
        if (!map.containsKey(counter)) {
            user.setId(++counter);
        }
        map.put(counter, user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public User findById(long id) {
        return map.get(id);
    }

    @Override
    public void deleteById(long id) {
        map.remove(id);
    }

}