package pl.codecouple.omomfood.offerservice.offer.domain;

import pl.codecouple.omomfood.offerservice.offer.dto.CreateOfferDto;
import pl.codecouple.omomfood.offerservice.offer.dto.GetAuthorDto;

/**
 * Created by CodeCouple.pl
 */
class AuthorCreator {

    GetAuthorDto from(CreateOfferDto createOfferDto){
        return GetAuthorDto.builder()
                .authorId(createOfferDto.getAuthorId())
                .build();
    }

}
