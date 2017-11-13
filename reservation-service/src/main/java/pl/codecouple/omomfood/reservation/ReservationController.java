package pl.codecouple.omomfood.reservation;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.codecouple.omomfood.reservation.domain.ReservationFacade;
import pl.codecouple.omomfood.reservation.dto.CreateReservationDTO;
import pl.codecouple.omomfood.reservation.dto.ReservationDTO;

/**
 * Created by CodeCouple.pl
 */
@RestController
@RequestMapping("/reservations")
class ReservationController {

    private final ReservationFacade facade;

    public ReservationController(ReservationFacade facade) {
        this.facade = facade;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    ReservationDTO createReservation(@RequestBody CreateReservationDTO reservationToCreate){
        return facade.createReservation(reservationToCreate);
    }

}
