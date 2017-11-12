package pl.codecouple.omomfood.offerservice.offer.domain;

import pl.codecouple.omomfood.offerservice.offer.dto.CreateOfferDto;
import pl.codecouple.omomfood.offerservice.offer.dto.GetAuthorDto;
import pl.codecouple.omomfood.offerservice.offer.dto.OfferDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by CodeCouple.pl
 */
public class OfferFacade {

    private final AuthorService authorService;
    private final AuthorCreator authorCreator;
    private final OfferRepository offerRepository;
    private final OfferCreator offerCreator;

    public OfferFacade(OfferRepository offerRepository,
                       OfferCreator offerCreator,
                       AuthorService authorService,
                       AuthorCreator authorCreator) {
        this.offerRepository = offerRepository;
        this.offerCreator = offerCreator;
        this.authorService = authorService;
        this.authorCreator = authorCreator;
}

    public OfferDto createOffer(CreateOfferDto offerToCreate) {
        GetAuthorDto getAuthorDto = authorCreator.from(offerToCreate);
        authorService.getAuthor(getAuthorDto);
        return offerRepository.save(offerCreator.from(offerToCreate)).dto();
    }

    public List<OfferDto> findAll(){
        return offerRepository.findAll().stream()
                .map(Offer::dto)
                .collect(toList());
    }

}
