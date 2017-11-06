package pl.codecouple.omomfood.offerservice.offer.domain

import pl.codecouple.omomfood.offerservice.offer.dto.CreateOfferDto
import spock.lang.Specification

class OfferFacadeSpec extends Specification {

    InMemoryOfferRepository offerRepository = new InMemoryOfferRepository()
    OfferFacade offerFacade = new OfferFacade(offerRepository)
    OfferCreator offerCreator = new OfferCreator()

    def "should add new offer to db"(){
        given:
            CreateOfferDto offerToAdd = new CreateOfferDto()
        when:
            offerFacade.add(offerToAdd)
        then:
            offerRepository.findAll().size() == 1
    }

    def "should return all offers from db"(){
        given:
            CreateOfferDto offerToAdd = new CreateOfferDto()
            offerRepository.save(offerCreator.from(offerToAdd))
        when:
            def result = offerFacade.findAll().size()
        then:
            result == 1
    }
}