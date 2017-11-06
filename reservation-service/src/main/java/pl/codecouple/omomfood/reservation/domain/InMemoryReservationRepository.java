package pl.codecouple.omomfood.reservation.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

/**
 * Created by CodeCouple.pl
 */
class InMemoryReservationRepository implements ReservationRepository {

    private ConcurrentHashMap<Long, Reservation> map = new ConcurrentHashMap<>();
    private long counter;

    @Override
    public Reservation save(Reservation reservation) {
        requireNonNull(reservation);
        reservation.setReservationID(++counter);
        map.put(counter, reservation);
        return reservation;
    }

    @Override
    public List<Reservation> findAll() {
        return new ArrayList<>(map.values());
    }
}
