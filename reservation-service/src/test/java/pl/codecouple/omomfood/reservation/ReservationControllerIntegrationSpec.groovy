package pl.codecouple.omomfood.reservation

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.client.RestTemplate
import pl.codecouple.omomfood.reservation.dto.CreateReservationDTO
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
/**
 * Created by CodeCouple.pl
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureWireMock
@AutoConfigureStubRunner(workOffline = true,
        ids = ["pl.codecouple.omomfood:user-service",
                "pl.codecouple.omomfood:offer-service"])
class ReservationControllerIntegrationSpec extends Specification {

    @Autowired
    MockMvc mockMvc

    @Autowired
    RestTemplate restTemplate

    private final ObjectMapper objectMapper = new ObjectMapper()

    def "Should return user not found" () {
        given:
            def createReservation = CreateReservationDTO.builder()
                    .quantity(1)
                    .offerID(1)
                    .userID(2)
                    .build()
        when:
            def result = mockMvc.perform(post("/reservations")
                    .content(objectMapper.writeValueAsString(createReservation))
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        then:
            result.andExpect(status().isNotFound())
    }

    def "Should return offer not found" () {
        given:
            def createReservation = CreateReservationDTO.builder()
                    .quantity(1)
                    .offerID(2)
                    .userID(1)
                    .build()
        when:
            def result = mockMvc.perform(post("/reservations")
                    .content(objectMapper.writeValueAsString(createReservation))
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        then:
            result.andExpect(status().isNotFound())
    }


    def "Should create new reservation"() {
        given:
            def createReservation = CreateReservationDTO.builder()
                    .quantity(1)
                    .offerID(1)
                    .userID(1)
                    .build()
        when:
            def result = mockMvc.perform(post("/reservations")
                    .content(objectMapper.writeValueAsString(createReservation))
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        then:
            result.andExpect(status().isCreated())
    }

}
