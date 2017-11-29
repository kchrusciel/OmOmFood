package pl.codecouple.omomfood.reservation

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import pl.codecouple.omomfood.reservation.domain.ReservationFacade
import pl.codecouple.omomfood.reservation.dto.CreateReservationDTO
import pl.codecouple.omomfood.reservation.dto.ReservationDTO
import pl.codecouple.omomfood.reservation.exceptions.OfferNotFound
import pl.codecouple.omomfood.reservation.exceptions.UserNotFound
import spock.lang.Specification

import static org.mockito.BDDMockito.given
import static org.mockito.Mockito.doNothing
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
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
                    .userID(1)
                    .offerID(1)
                    .build()
            given(facade.createReservation(reservationToCreate)).willThrow(OfferNotFound)
        when:
            def result = reservation.perform(post("/reservations")
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(objectMapper.writeValueAsString(reservationToCreate)))
        then:
            result.andExpect(status().isNotFound())
                    .andExpect(status().reason("Offer not found"))
    }

    def "Should return 'User not found' message when author ID not exists"() {
        given:
            def reservationToCreate = CreateReservationDTO.builder()
                    .userID(1)
                    .offerID(1)
                    .build()
            given(facade.createReservation(reservationToCreate)).willThrow(UserNotFound)
        when:
            def result = reservation.perform(post("/reservations")
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(objectMapper.writeValueAsString(reservationToCreate)))
        then:
            result.andExpect(status().isNotFound())
                    .andExpect(status().reason("User not found"))
    }

    def "Should create new reservation"() {
        given:
            def reservationToCreate = CreateReservationDTO.builder()
                    .userID(1)
                    .offerID(1)
                    .build()
            def reservationToReturn = ReservationDTO.builder()
                    .id(1)
                    .offerID(0)
                    .userID(0)
                    .build()
            given(facade.createReservation(reservationToCreate)).willReturn(reservationToReturn)
        when:
            def result = reservation.perform(post("/reservations")
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(objectMapper.writeValueAsString(reservationToCreate)))
        then:
            result.andExpect(status().isCreated())
                    .andExpect(content().json(objectMapper.writeValueAsString(reservationToReturn)))
    }

    def "Should return reservation by ID"() {
        given:
            def reservationToReturn = ReservationDTO.builder()
                    .id(1)
                    .offerID(0)
                    .userID(0)
                    .build()
            given(facade.getReservationById(reservationToReturn.id)).willReturn(reservationToReturn)
        when:
            def result = reservation.perform(get("/reservations/1"))
        then:
            result.andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(reservationToReturn)))
    }

    def "Should return all reservations from DB"() {
        given:
            def reservationToReturn = ReservationDTO.builder()
                    .id(1)
                    .offerID(0)
                    .userID(0)
                    .build()
            def reservations = Collections.singletonList(reservationToReturn)
            given(facade.findAll()).willReturn(reservations)
        when:
            def result = reservation.perform(get("/reservations"))
        then:
            result.andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(reservations)))
    }

    def "Should delete reservation by ID"() {
        given:
            doNothing().when(facade).delete(1)
        when:
            def result = reservation.perform(delete("/reservations/1"))
        then:
            result.andExpect(status().isNoContent())
    }

    def "Should update reservation by ID"() {
        given:
            def reservationToUpdate = ReservationDTO.builder()
                    .id(1)
                    .offerID(1)
                    .userID(1)
                    .build()
            given(facade.update(reservationToUpdate)).willReturn(reservationToUpdate)
        when:
            def result = reservation.perform(put("/reservations/1")
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(objectMapper.writeValueAsString(reservationToUpdate)))
        then:
            result.andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(reservationToUpdate)))
    }

    def "Should partial update reservation by ID"() {
        given:
            def reservationToUpdate = ReservationDTO.builder()
                    .id(1)
                    .offerID(1)
                    .userID(1)
                    .build()
            given(facade.updateFields(1, new HashMap<String, Object>())).willReturn(reservationToUpdate)
        when:
            def result = reservation.perform(patch("/reservations/1")
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(objectMapper.writeValueAsString(new HashMap<String, Object>())))
        then:
            result.andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(reservationToUpdate)))
    }

    def "Should return 404 when field in new reservation is missing or is equal to 0"() {
        given:
            def reservationToAdd = ReservationDTO.builder()
                    .id(1)
                    .offerID(0)
                    .build()
        when:
            def result = reservation.perform(put("/reservations/1")
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(objectMapper.writeValueAsString(reservationToAdd)))
        then:
            result.andExpect(status().isNotFound())
    }


}