package pl.codecouple.omomfood.offer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.codecouple.omomfood.offer.domain.OfferFacade;
import pl.codecouple.omomfood.offer.dto.OfferDto;
import pl.codecouple.omomfood.offer.dto.CreateOfferDto;

import java.util.List;
import java.util.Map;

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
    @ResponseStatus(HttpStatus.OK)
    List<OfferDto> findAllOffers() {
        return offerFacade.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    OfferDto getOfferById(@PathVariable("id") long id) {
        return offerFacade.findById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    OfferDto update(@RequestBody OfferDto offerToUpdate) {
        return offerFacade.update(offerToUpdate);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    OfferDto updateFields(@PathVariable("id") long id, @RequestBody Map<String, Object> fieldsToUpdate) {
        return offerFacade.updateFields(id, fieldsToUpdate);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") long id) {
        offerFacade.delete(id);
    }
}
