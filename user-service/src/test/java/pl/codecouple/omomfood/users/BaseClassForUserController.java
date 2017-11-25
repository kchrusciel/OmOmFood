package pl.codecouple.omomfood.users;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.codecouple.omomfood.UserServiceApplication;
import pl.codecouple.omomfood.users.domain.UserFacade;
import pl.codecouple.omomfood.users.dto.UserDTO;
import pl.codecouple.omomfood.users.exceptions.UserNotFound;

import static org.mockito.BDDMockito.given;

/**
 * Created by CodeCouple.pl
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserServiceApplication.class)
@AutoConfigureMockMvc
public abstract class BaseClassForUserController {

    @Autowired
    UserController userController;

    @MockBean
    UserFacade userFacade;

    @Before
    public void setUp() throws Exception {
        RestAssuredMockMvc.standaloneSetup(userController);
        given(userFacade.getUserById(1)).willReturn(UserDTO.builder()
                .id(1)
                .username("username")
                .build());
        given(userFacade.getUserById(2)).willThrow(new UserNotFound());
    }
}
