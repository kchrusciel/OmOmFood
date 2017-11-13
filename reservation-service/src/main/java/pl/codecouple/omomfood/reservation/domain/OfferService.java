package pl.codecouple.omomfood.reservation.domain;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.codecouple.omomfood.reservation.dto.GetOfferDTO;
import pl.codecouple.omomfood.reservation.dto.OfferDTO;
import pl.codecouple.omomfood.reservation.exceptions.OfferNotFound;

/**
 * Created by CodeCouple.pl
 */
@Service
class OfferService {

    private final RestTemplate restTemplate;

    OfferService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    OfferDTO getOffer(GetOfferDTO getOfferDTO) {
        ResponseEntity<OfferDTO> offer = getForEntity(getOfferDTO);
        if (offer == null || offer.getStatusCode() != HttpStatus.OK) {
            throw new OfferNotFound();
        }
        return offer.getBody();
    }

    boolean isOfferAvailable(GetOfferDTO getOfferDTO) {
        ResponseEntity<OfferDTO> offer = getForEntity(getOfferDTO);
        if (offer == null || offer.getStatusCode() != HttpStatus.OK) {
            throw new OfferNotFound();
        }
        return true;
    }

    @HystrixCommand(fallbackMethod = "offerNotFound")
    private ResponseEntity<OfferDTO> getForEntity(GetOfferDTO getOfferDTO){
        return restTemplate.getForEntity(
                ServiceAddress.OFFER_SERVICE_URI + "/{offerID}", OfferDTO.class, getOfferDTO.getOfferID());
    }

    OfferDTO offerNotFound(GetOfferDTO getOfferDTO) {
        throw new OfferNotFound();
    }


}
