package pl.codecouple.authservice.principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by CodeCouple.pl
 */
@RestController
class PrincipalController {

        @GetMapping({ "/user", "/me" })
        public Principal user(Principal principal) {
            return principal;
        }

}
