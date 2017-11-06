package pl.codecouple.omomfood.reservation.domain;

import org.springframework.stereotype.Service;
import pl.codecouple.omomfood.reservation.dto.GetAuthorDTO;
import pl.codecouple.omomfood.reservation.exceptions.AuthorNotFound;

/**
 * Created by CodeCouple.pl
 */
@Service
class AuthorService {

    GetAuthorDTO getAuthor(GetAuthorDTO getAuthorDTO){
        throw new AuthorNotFound();
    }

}
