package pl.codecouple.omomfood.offer.domain;

import org.springframework.stereotype.Service;
import pl.codecouple.omomfood.offer.dto.GetAuthorDto;
import pl.codecouple.omomfood.offer.exceptions.AuthorNotFound;

/**
 * Created by CodeCouple.pl
 */
@Service
class AuthorService {

    GetAuthorDto getAuthor(GetAuthorDto getAuthorDto){
        throw new AuthorNotFound();
    }

}
