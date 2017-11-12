package pl.codecouple.omomfood.offerservice.offer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.codecouple.omomfood.offerservice.offer.domain.OfferFacade;
import pl.codecouple.omomfood.offerservice.offer.dto.CreateOfferDto;
import pl.codecouple.omomfood.offerservice.offer.dto.OfferDto;

import java.util.List;

/**
 * Created by CodeCouple.pl
 */
@RestController
@RequestMapping("/offers")
class OfferController {

    private OfferFacade offerFacade;

    public OfferController(OfferFacade offerFacade) {
        this.offerFacade = offerFacade;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    OfferDto createOffer(@RequestBody CreateOfferDto offerToCreate) {
        return offerFacade.createOffer(offerToCreate);
    }

    @GetMapping
    List<OfferDto> findAllOffers() {
        return offerFacade.findAll();
    }
}
