package pl.codecouple.omomfood.reservation.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by CodeCouple.pl
 */
@Configuration
class ReservationConfiguration {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    OfferServiceGetter offerServiceGetter() {
        return new OfferServiceGetter(restTemplate());
    }

    @Bean
    UserServiceGetter userServiceGetter() {
        return new UserServiceGetter(restTemplate());
    }

    ReservationFacade reservationFacade() {
        return reservationFacade(new UserService(userServiceGetter()),
                new OfferService(offerServiceGetter()),
                new InMemoryReservationRepository());
    }

    @Bean
    ReservationFacade reservationFacade(
            UserService userService,
            OfferService offerService,
            ReservationRepository repository) {
        UserCreator userCreator = new UserCreator();
        OfferCreator offerCreator = new OfferCreator();
        ReservationCreator reservationCreator = new ReservationCreator();
        return new ReservationFacade(userService, offerService, userCreator, offerCreator, repository, reservationCreator);
    }

}
