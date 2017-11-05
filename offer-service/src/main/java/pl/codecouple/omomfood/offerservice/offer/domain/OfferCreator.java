package pl.codecouple.omomfood.offerservice.offer.domain;

import pl.codecouple.omomfood.offerservice.offer.dto.OfferDto;

import static java.util.Objects.requireNonNull;

class OfferCreator {
    Offer from(OfferDto offerDto) {
        requireNonNull(offerDto);
        return Offer.builder()
                .title(offerDto.getTitle())
                .content(offerDto.getContent())
                .userId(offerDto.getUserId())
                .build();
    }
}
