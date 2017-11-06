package pl.codecouple.omomfood.reservation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.codecouple.omomfood.reservation.domain.ReservationFacade;
import pl.codecouple.omomfood.reservation.dto.CreateReservationDTO;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    String createReservation(CreateReservationDTO reservationToCreate){
        facade.createReservation(reservationToCreate);
        return "Reservation created";
    }

}
