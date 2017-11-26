package pl.codecouple.omomfood.offer;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.codecouple.omomfood.OfferServiceApplication;
import pl.codecouple.omomfood.offer.domain.OfferFacade;
import pl.codecouple.omomfood.offer.dto.OfferDto;
import pl.codecouple.omomfood.offer.exceptions.OfferNotFound;

import static org.mockito.BDDMockito.given;

/**
 * Created by CodeCouple.pl
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OfferServiceApplication.class)
@AutoConfigureMockMvc
public abstract class BaseClassForOfferController {

    @Autowired
    OfferController offerController;

    @MockBean
    OfferFacade offerFacade;

    @Before
    public void setUp() throws Exception {
        RestAssuredMockMvc.standaloneSetup(offerController);
        given(offerFacade.findById(1)).willReturn(OfferDto.builder()
                .id(1)
                .authorId(1)
                .content("content")
                .title("title")
                .build());
        given(offerFacade.findById(2)).willThrow(new OfferNotFound());
    }

}
