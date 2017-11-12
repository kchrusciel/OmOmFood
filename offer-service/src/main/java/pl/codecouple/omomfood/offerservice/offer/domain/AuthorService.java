package pl.codecouple.omomfood.offerservice.offer.domain;

import org.springframework.stereotype.Service;
import pl.codecouple.omomfood.offerservice.offer.dto.GetAuthorDto;
import pl.codecouple.omomfood.offerservice.offer.exceptions.AuthorNotFound;

/**
 * Created by CodeCouple.pl
 */
@Service
class AuthorService {

    GetAuthorDto getAuthor(GetAuthorDto getAuthorDto){
        throw new AuthorNotFound();
    }

}
