package pl.codecouple.omomfood.reservation.domain

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import pl.codecouple.omomfood.reservation.dto.GetOfferDTO
import pl.codecouple.omomfood.reservation.dto.OfferDTO
import pl.codecouple.omomfood.reservation.exceptions.OfferNotFound
import spock.lang.Specification
/**
 * Created by CodeCouple.pl
 */
class OfferServiceSpec extends Specification {

    OfferServiceGetter getter = Mock()
    OfferService service = new OfferService(getter)


    def "Should throw 'Offer not found' when status from offer-service is different than 200"(){
        given:
            def offer = GetOfferDTO.builder().offerID(1).build()
            getter.getOffer(offer) >> { return new ResponseEntity<OfferDTO>(HttpStatus.NOT_FOUND) }
        when:
            service.getOffer(offer)
        then:
            OfferNotFound e = thrown()
            e.message == "Offer not found"
    }

    def "Should return offer id when offer exists in offer-service" () {
        given:
            def getOffer = GetOfferDTO.builder().offerID(2).build()
            def offer = OfferDTO.builder().offerID(2).build()
            getter.getOffer(getOffer) >> { return new ResponseEntity<OfferDTO>(offer, HttpStatus.OK) }
        when:
            def result = service.getOffer(getOffer)
        then:
            result.getOfferID() == 2
    }

    def "Should return true when offer is available in offer-service" () {
        given:
            def getOffer = GetOfferDTO.builder().offerID(2).build()
            def offer = OfferDTO.builder().offerID(2).build()
            getter.getOffer(getOffer) >> { return new ResponseEntity<OfferDTO>(offer, HttpStatus.OK) }
        when:
            def result = service.isOfferAvailable(getOffer)
        then:
            result
    }

}