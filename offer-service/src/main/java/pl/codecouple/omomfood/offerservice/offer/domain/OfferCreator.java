package pl.codecouple.omomfood.offerservice.offer.domain;

import pl.codecouple.omomfood.offerservice.offer.dto.CreateOfferDto;
import pl.codecouple.omomfood.offerservice.offer.dto.OfferDto;

import static java.util.Objects.requireNonNull;

/**
 * Created by CodeCouple.pl
 */
class OfferCreator {
    Offer from(CreateOfferDto createOfferDto) {
        requireNonNull(createOfferDto);
        return Offer.builder()
                .title(createOfferDto.getTitle())
                .content(createOfferDto.getContent())
                .authorId(createOfferDto.getAuthorId())
                .build();
    }

    Offer from(OfferDto offerDto) {
        requireNonNull(offerDto);
        return Offer.builder()
                .id(offerDto.getId())
                .title(offerDto.getTitle())
                .content(offerDto.getContent())
                .authorId(offerDto.getAuthorId())
                .build();
    }
}
