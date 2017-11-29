package pl.codecouple.omomfood.reservation;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.codecouple.omomfood.reservation.domain.ReservationFacade;
import pl.codecouple.omomfood.reservation.dto.CreateReservationDTO;
import pl.codecouple.omomfood.reservation.dto.ReservationDTO;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by CodeCouple.pl
 */
@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationFacade facade;

    public ReservationController(ReservationFacade facade) {
        this.facade = facade;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    ReservationDTO createReservation(@Valid @RequestBody CreateReservationDTO reservationToCreate){
        return facade.createReservation(reservationToCreate);
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ReservationDTO getReservationByID(@PathVariable("id") long id){
        return facade.getReservationById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<ReservationDTO> findAll(){
        return facade.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteReservationByID (@PathVariable("id") long id){
        facade.delete(id);
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    ReservationDTO update(@Valid @RequestBody ReservationDTO reservationToUpdate){
        return facade.update(reservationToUpdate);
    }

    @PatchMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    ReservationDTO updateFields(@PathVariable("id") long id, @RequestBody Map<String, Object> fieldsToUpdate){
        return facade.updateFields(id, fieldsToUpdate);
    }



}
