package pl.codecouple.omomfood.offerservice.offer.domain;

import pl.codecouple.omomfood.offerservice.offer.dto.CreateOfferDto;

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
}
