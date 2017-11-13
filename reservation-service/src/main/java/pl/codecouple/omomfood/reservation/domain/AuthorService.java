package pl.codecouple.omomfood.reservation.domain;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.codecouple.omomfood.reservation.dto.AuthorDTO;
import pl.codecouple.omomfood.reservation.dto.GetAuthorDTO;
import pl.codecouple.omomfood.reservation.exceptions.AuthorNotFound;

/**
 * Created by CodeCouple.pl
 */
@Service
class AuthorService {

    private final RestTemplate restTemplate;

    AuthorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    AuthorDTO getAuthor(GetAuthorDTO getAuthorDTO) {
        ResponseEntity<AuthorDTO> author = getForEntity(getAuthorDTO);
        if (author == null || author.getStatusCode() != HttpStatus.OK) {
            throw new AuthorNotFound();
        }
        return author.getBody();
    }

    boolean isAuthorAvailable(GetAuthorDTO getAuthorDTO) {
        ResponseEntity<AuthorDTO> offer = getForEntity(getAuthorDTO);
        if (offer == null || offer.getStatusCode() != HttpStatus.OK) {
            throw new AuthorNotFound();
        }
        return true;
    }

    @HystrixCommand(fallbackMethod = "authorNotFound")
    private ResponseEntity<AuthorDTO> getForEntity(GetAuthorDTO getAuthorDTO) {
        return restTemplate.getForEntity(
                ServiceAddress.AUTHOR_SERVICE_URI + "/{authorID}", AuthorDTO.class, getAuthorDTO.getAuthorID());
    }

    AuthorDTO authorNotFound(GetAuthorDTO getAuthorDTO){
        throw new AuthorNotFound();
    }

}
