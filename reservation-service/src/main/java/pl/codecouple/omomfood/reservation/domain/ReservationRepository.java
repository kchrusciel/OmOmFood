package pl.codecouple.omomfood.reservation.domain;

import org.springframework.data.repository.Repository;
import pl.codecouple.omomfood.reservation.exceptions.ReservationNotFound;

import java.util.List;

/**
 * Created by CodeCouple.pl
 */
interface ReservationRepository extends Repository<Reservation, Long> {

    List<Reservation> findAll();

    Reservation save(Reservation reservation);

    Reservation findById(long id);

    void deleteById(long id);

    default Reservation findByIdOrThrow(long id) {
        Reservation reservation = findById(id);
        if (reservation == null) {
            throw new ReservationNotFound();
        }
        return reservation;
    }

}
