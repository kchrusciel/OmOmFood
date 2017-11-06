package pl.codecouple.omomfood.offerservice.offer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.codecouple.omomfood.offerservice.offer.domain.OfferFacade;
import pl.codecouple.omomfood.offerservice.offer.dto.CreateOfferDto;
import pl.codecouple.omomfood.offerservice.offer.dto.OfferDto;

import java.util.List;

@RestController
@RequestMapping("/offers")
class OfferController {

    private OfferFacade offerFacade;

    public OfferController(OfferFacade offerFacade) {
        this.offerFacade = offerFacade;
    }

    @PostMapping
    String addOffer(CreateOfferDto offerToAdd) {
        offerFacade.add(offerToAdd);
        return "Offer created";
    }

    @GetMapping
    List<OfferDto> findAllOffers() {
        return offerFacade.findAll();
    }
}
