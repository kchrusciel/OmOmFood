package pl.codecouple.omomfood.reservation.domain;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.codecouple.omomfood.reservation.dto.GetUserDTO;
import pl.codecouple.omomfood.reservation.dto.UserDTO;
import pl.codecouple.omomfood.reservation.exceptions.UserNotFound;

/**
 * Created by CodeCouple.pl
 */
@Slf4j
@Component
class UserServiceGetter {

    private final RestTemplate restTemplate;

    UserServiceGetter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "userNotFound")
    public ResponseEntity<UserDTO> getUser(GetUserDTO getUserDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(
                ServiceAddress.AUTHOR_SERVICE_URI + "/users/{id}",
                HttpMethod.GET,
                entity,
                UserDTO.class,
                getUserDTO.getUserID()
        );
    }

    public ResponseEntity<UserDTO> userNotFound(GetUserDTO getUserDTO){
        throw new UserNotFound();
    }
}
