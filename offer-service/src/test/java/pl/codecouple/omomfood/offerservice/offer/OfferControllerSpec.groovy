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
import pl.codecouple.omomfood.offerservice.offer.exceptions.OfferNotFound
import spock.lang.Specification

import static org.mockito.Mockito.doNothing
import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
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

    def "Should throw 'Author not found' when create offer and author ID not exists"() {
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

    def "Should throw 'Offer not found' when update offer which not exists"() {
        given:
            def offerToUpdate = OfferDto.builder()
                    .id(0)
                    .build()
            when(facade.update(offerToUpdate)).thenThrow(OfferNotFound)
        when:
            def result = offer.perform(put("/offers")
                    .content(mapper.writeValueAsString(offerToUpdate))
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
        then:
            result.andExpect(status().isNotFound())
                    .andExpect(status().reason("Offer not found"))
    }

    def "should return updated offer when update offer"() {
        given:
            def offerToUpdate = OfferDto.builder()
                    .id(1)
                    .build()
            when(facade.update(offerToUpdate)).thenReturn(offerToUpdate)
        when:
            def result = offer.perform(put("/offers")
                    .content(mapper.writeValueAsString(offerToUpdate))
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
        then:
            result.andExpect(content().json(mapper.writeValueAsString(offerToUpdate)))
                    .andExpect(status().isNoContent())
    }

    def "should return updated offer when partial update offer"() {
        given:
            def offerToUpdate = OfferDto.builder()
                    .id(1)
                    .build()
            when(facade.updateFields(1, new HashMap<String, Object>())).thenReturn(offerToUpdate)
        when:
            def result = offer.perform(patch("/offers/1")
                    .content(mapper.writeValueAsString(new HashMap<String, Object>()))
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
        then:
            result.andExpect(content().json(mapper.writeValueAsString(offerToUpdate)))
                    .andExpect(status().isNoContent())
    }

    def "should return 'Offer deleted' when offer is deleted"() {
        given:
            doNothing().when(facade).delete(1)
        when:
            def result = offer.perform(delete("/offers/1"))
        then:
            result.andExpect(status().isNoContent())
    }
}