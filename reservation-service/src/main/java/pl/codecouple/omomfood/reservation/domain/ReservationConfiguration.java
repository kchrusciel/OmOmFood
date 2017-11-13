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
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    ReservationFacade reservationFacade() {
        return reservationFacade(new AuthorService(restTemplate()),
                                 new OfferService(restTemplate()),
                                 new InMemoryReservationRepository());
    }

    @Bean
    ReservationFacade reservationFacade(
            AuthorService authorService,
            OfferService offerService,
            ReservationRepository repository){
        AuthorCreator authorCreator = new AuthorCreator();
        OfferCreator offerCreator = new OfferCreator();
        ReservationCreator reservationCreator = new ReservationCreator();
        return new ReservationFacade(authorService, offerService, authorCreator, offerCreator, repository, reservationCreator);
    }

}
