package pl.codecouple.omomfood.user.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by CodeCouple.pl
 */
@Configuration
class UserConfiguration {

    @Bean
    UserFacade userFacade(UserRepository userRepository) {
        UserCreator userCreator = new UserCreator();
        return new UserFacade(userRepository, userCreator);
    }

}
