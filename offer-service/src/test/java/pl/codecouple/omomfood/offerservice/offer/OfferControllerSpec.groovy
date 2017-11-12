package pl.codecouple.omomfood.offerservice.offer

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import pl.codecouple.omomfood.offerservice.offer.domain.OfferFacade
import pl.codecouple.omomfood.offerservice.offer.dto.CreateOfferDto
import pl.codecouple.omomfood.offerservice.offer.dto.OfferDto
import pl.codecouple.omomfood.offerservice.offer.exceptions.AuthorNotFound
import spock.lang.Specification

import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * Created by CodeCouple.pl
 */
@WebMvcTest(OfferController)
class OfferControllerSpec extends Specification {

    @Autowired
    MockMvc offer
    @MockBean
    OfferFacade facade
    ObjectMapper mapper = new ObjectMapper()

    def "Should throw 'Author not found' when author ID not exists"() {
        given:
            def offerToCreate = CreateOfferDto.builder()
                    .authorId(0)
                    .build()
            when(facade.createOffer(offerToCreate)).thenThrow(AuthorNotFound)
        when:
            def result = offer.perform(post("/offers")
                    .content(mapper.writeValueAsString(offerToCreate))
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
        then:
            result.andExpect(status().isNotFound())
                    .andExpect(status().reason("Author not found"))
    }

    def "Should return new offer when offer is created"() {
        given:
            def createdOffer = OfferDto.builder()
                    .id(1)
                    .authorId(1)
                    .title("title")
                    .content("content")
                    .build()
            def offerToCreate = CreateOfferDto.builder()
                    .authorId(1)
                    .title("title")
                    .content("content")
                    .build()
            when(facade.createOffer(offerToCreate)).thenReturn(createdOffer)
        when:
            def result = offer.perform(post("/offers")
                    .content(mapper.writeValueAsString(offerToCreate))
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
        then:
            result.andExpect(content().string(mapper.writeValueAsString(createdOffer)))
                    .andExpect(status().isCreated())
    }

    def "Should return list of all offers when get '/offers'"() {
        given:
            def offerFromDb = OfferDto.builder()
                    .id(1)
                    .authorId(1)
                    .title('title')
                    .content("content")
                    .build()
            def offers = Collections.singletonList(offerFromDb)
            when(facade.findAll()).thenReturn(offers)
        when:
            def result = offer.perform(get("/offers"))
        then:
            result.andExpect(content().json(mapper.writeValueAsString(offers)))
                    .andExpect(status().isOk())
    }

    def "should return 'no offer found' when offer with given id didn't exist"() {

    }

    def "should return 'offer deleted' when offer is deleted"() {

    }

    def "should return updated offer when offer is updated"() {

    }


}