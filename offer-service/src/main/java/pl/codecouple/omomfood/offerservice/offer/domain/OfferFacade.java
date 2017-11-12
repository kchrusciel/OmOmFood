package pl.codecouple.omomfood.offerservice.offer.domain;

import pl.codecouple.omomfood.offerservice.offer.dto.CreateOfferDto;
import pl.codecouple.omomfood.offerservice.offer.dto.GetAuthorDto;
import pl.codecouple.omomfood.offerservice.offer.dto.OfferDto;
import pl.codecouple.omomfood.offerservice.offer.exceptions.OfferNotFound;

import java.util.List;
import java.util.Map;

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

    public OfferDto findById(long id) {
        Offer offerFromDb = offerRepository.findByIdOrThrow(id);
        return offerFromDb.dto();
    }

    public void delete(long id) {
        offerRepository.findByIdOrThrow(id);
        offerRepository.deleteById(id);
    }

    public OfferDto update(OfferDto offerToUpdate) {
        offerRepository.findByIdOrThrow(offerToUpdate.getId());
        return offerRepository.save(offerCreator.from(offerToUpdate)).dto();
    }

    public OfferDto updateFields(long id, Map<String, Object> fieldsToUpdate) {
        Offer offer = offerRepository.findByIdOrThrow(id);
        if (fieldsToUpdate.containsKey("title")) {
            offer.setTitle(String.valueOf(fieldsToUpdate.get("title")));
        } else if (fieldsToUpdate.containsKey("content")) {
            offer.setContent(String.valueOf(fieldsToUpdate.get("content")));
        }
        return offerRepository.save(offer).dto();
    }
}
