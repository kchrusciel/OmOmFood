package pl.codecouple.omomfood.offer.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by CodeCouple.pl
 */
@Configuration
class OfferConfiguration {
    OfferFacade offerFacade() {
        return offerFacade(new InMemoryOfferRepository(), new AuthorService());
    }

    @Bean
    OfferFacade offerFacade(OfferRepository offerRepository, AuthorService authorService) {
        OfferCreator offerCreator = new OfferCreator();
        AuthorCreator authorCreator = new AuthorCreator();
        return new OfferFacade(offerRepository, offerCreator, authorService, authorCreator);
    }
}
