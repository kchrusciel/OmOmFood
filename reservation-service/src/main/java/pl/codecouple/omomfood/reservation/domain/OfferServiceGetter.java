package pl.codecouple.omomfood.reservation.domain;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.codecouple.omomfood.reservation.dto.GetOfferDTO;
import pl.codecouple.omomfood.reservation.dto.OfferDTO;
import pl.codecouple.omomfood.reservation.exceptions.OfferNotFound;

/**
 * Created by CodeCouple.pl
 */
@Slf4j
@Component
class OfferServiceGetter {

    private final RestTemplate restTemplate;

    OfferServiceGetter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "offerNotFound")
    ResponseEntity<OfferDTO> getOffer(GetOfferDTO getOfferDTO){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(
                ServiceAddress.OFFER_SERVICE_URI + "/offers/{offerID}",
                HttpMethod.GET,
                entity,
                OfferDTO.class,
                getOfferDTO.getOfferID()
        );
    }

    ResponseEntity<OfferDTO> offerNotFound(GetOfferDTO getOfferDTO) {
        log.debug("Offer not found");
        throw new OfferNotFound();
    }

}
