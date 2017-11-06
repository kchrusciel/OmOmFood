package pl.codecouple.omomfood.offerservice.offer.domain;

import pl.codecouple.omomfood.offerservice.offer.dto.CreateOfferDto;
import pl.codecouple.omomfood.offerservice.offer.dto.OfferDto;

import java.util.List;
import java.util.stream.Collectors;

public class OfferFacade {

    private final OfferRepository offerRepository;
    private final OfferCreator offerCreator;

    public OfferFacade(OfferRepository offerRepository, OfferCreator offerCreator) {
        this.offerRepository = offerRepository;
        this.offerCreator = offerCreator;
}

    public void add(CreateOfferDto offerToAdd) {
        offerRepository.save(offerCreator.from(offerToAdd));
    }

    public List<OfferDto> findAll(){
        return offerRepository.findAll().stream()
                .map(Offer::dto)
                .collect(Collectors.toList());
    }

}
