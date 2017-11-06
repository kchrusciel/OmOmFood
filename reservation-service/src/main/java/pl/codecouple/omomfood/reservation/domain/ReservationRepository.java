package pl.codecouple.omomfood.reservation.domain;

import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by CodeCouple.pl
 */
interface ReservationRepository extends Repository<Reservation, Long> {

    List<Reservation> findAll();
    Reservation save(Reservation reservation);

}
