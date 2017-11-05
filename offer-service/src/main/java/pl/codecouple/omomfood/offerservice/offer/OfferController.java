package pl.codecouple.omomfood.offerservice.offer;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.codecouple.omomfood.offerservice.offer.dto.OfferDto;

@RestController
@RequestMapping("/offers")
public class OfferController {

    @PostMapping
    public String addOffer(OfferDto offerToAdd) {
        return "Offer created";
    }
}
