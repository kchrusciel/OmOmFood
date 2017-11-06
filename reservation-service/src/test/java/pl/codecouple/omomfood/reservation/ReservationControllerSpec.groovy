package pl.codecouple.omomfood.reservation

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import pl.codecouple.omomfood.reservation.domain.ReservationFacade
import pl.codecouple.omomfood.reservation.dto.CreateReservationDTO
import pl.codecouple.omomfood.reservation.exceptions.AuthorNotFound
import pl.codecouple.omomfood.reservation.exceptions.OfferNotFound
import spock.lang.Specification

import static org.mockito.Mockito.doNothing
import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
/**
 * Created by CodeCouple.pl
 */
@WebMvcTest(ReservationController)
class ReservationControllerSpec extends Specification {

    @Autowired
    MockMvc reservation

    @MockBean
    ReservationFacade facade

    private final ObjectMapper objectMapper = new ObjectMapper()

    def "Should return 'Offer not found' message when offer ID not exists"() {
        given:
            def reservationToCreate = CreateReservationDTO.builder()
                    .authorID(0)
                    .offerID(0)
                    .quantity(0)
                    .build()
            when(facade.createReservation(reservationToCreate)).thenThrow(OfferNotFound)
        when:
            def result = reservation.perform(post("/reservations")
                    .content(objectMapper.writeValueAsString(reservationToCreate)))
        then:
            result.andExpect(status().isNotFound())
                    .andExpect(status().reason("Offer not found"))
    }

    def "Should return 'Author not found' message when author ID not exists"() {
        given:
            def reservationToCreate = CreateReservationDTO.builder()
                    .authorID(0)
                    .offerID(0)
                    .quantity(0)
                    .build()
            when(facade.createReservation(reservationToCreate)).thenThrow(AuthorNotFound)
        when:
            def result = reservation.perform(post("/reservations")
                    .content(objectMapper.writeValueAsString(reservationToCreate)))
        then:
            result.andExpect(status().isNotFound())
                    .andExpect(status().reason("Author not found"))
    }

    def "Should create new reservation" () {
        given:
            def reservationToCreate = CreateReservationDTO.builder()
                    .authorID(0)
                    .offerID(0)
                    .quantity(0)
                    .build()
            doNothing().when(facade).createReservation(reservationToCreate)
        when:
            def result = reservation.perform(post("/reservations")
                    .content(objectMapper.writeValueAsString(reservationToCreate)))
        then:
            result.andExpect(status().isCreated())
                    .andExpect(content().string("Reservation created"))
    }


}