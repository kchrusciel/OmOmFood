package pl.codecouple.omomfood.offerservice.offer.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OfferConfiguration {
    OfferFacade offerFacade() {
        return offerFacade(new InMemoryOfferRepository());
    }

    @Bean
    OfferFacade offerFacade(OfferRepository offerRepository) {
        OfferCreator offerCreator = new OfferCreator();
        return new OfferFacade(offerRepository, offerCreator);
    }
}
