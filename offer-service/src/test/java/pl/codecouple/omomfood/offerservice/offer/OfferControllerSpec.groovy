package pl.codecouple.omomfood.offerservice.offer

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.web.servlet.MockMvc
import pl.codecouple.omomfood.offerservice.offer.domain.OfferFacade
import pl.codecouple.omomfood.offerservice.offer.dto.OfferDto
import spock.lang.Specification
import spock.mock.DetachedMockFactory

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content


@WebMvcTest(OfferController)
class OfferControllerSpec extends Specification {

    @Autowired
    MockMvc mvc
    ObjectMapper mapper = new ObjectMapper()


    def "should return 'Offer created' when offer is created"() {
        given:
            def offerToAdd = new OfferDto()
        when:
            def result = mvc.perform(post("/offers").content(mapper.writeValueAsString(offerToAdd)))
        then:
            result.andExpect(content().string("Offer created"))
    }

    def "should return 'no offer found' when offer with given id didn't exist"() {

    }

    def "should return 'offer deleted' when offer is deleted"() {

    }

    def "should return updated offer when offer is updated"() {

    }


}