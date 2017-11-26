package pl.codecouple.omomfood.offer.domain;

import pl.codecouple.omomfood.offer.dto.CreateOfferDto;
import pl.codecouple.omomfood.offer.dto.GetAuthorDto;

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
