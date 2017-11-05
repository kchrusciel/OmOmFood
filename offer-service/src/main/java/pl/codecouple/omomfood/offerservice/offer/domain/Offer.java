package pl.codecouple.omomfood.offerservice.offer.domain;

import lombok.Builder;
import pl.codecouple.omomfood.offerservice.offer.dto.OfferDto;

@Builder
class Offer {
    private String title;
    private String content;
    private long userId;

    OfferDto dto() {
        return OfferDto.builder()
                .title(title)
                .content(content)
                .userId(userId)
                .build();
    }
}
