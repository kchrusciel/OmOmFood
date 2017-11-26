package pl.codecouple.omomfood.reservation.domain;

import pl.codecouple.omomfood.reservation.dto.CreateReservationDTO;
import pl.codecouple.omomfood.reservation.dto.GetUserDTO;
import pl.codecouple.omomfood.reservation.dto.GetOfferDTO;
import pl.codecouple.omomfood.reservation.dto.ReservationDTO;
import pl.codecouple.omomfood.reservation.exceptions.CannotCreateReservation;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * Created by CodeCouple.pl
 */
public class ReservationFacade {

    private final UserService userService;
    private final OfferService offerService;
    private final UserCreator userCreator;
    private final OfferCreator offerCreator;
    private final ReservationRepository repository;
    private final ReservationCreator reservationCreator;

    public ReservationFacade(UserService userService,
                             OfferService offerService,
                             UserCreator userCreator,
                             OfferCreator offerCreator,
                             ReservationRepository repository, ReservationCreator reservationCreator) {
        this.userService = userService;
        this.offerService = offerService;
        this.userCreator = userCreator;
        this.offerCreator = offerCreator;
        this.repository = repository;
        this.reservationCreator = reservationCreator;
    }

    public ReservationDTO createReservation(CreateReservationDTO reservationToCreate) {
        GetUserDTO getUserDTO = userCreator.from(reservationToCreate);
        GetOfferDTO getOfferDTO = offerCreator.from(reservationToCreate);
        if (userService.isUserAvailable(getUserDTO) && offerService.isOfferAvailable(getOfferDTO)) {
            return repository.save(reservationCreator.from(reservationToCreate)).dto();
        }
        throw new CannotCreateReservation();
    }

    public List<ReservationDTO> findAll() {
        return repository.findAll().stream()
                .map(Reservation::dto)
                .collect(toList());
    }


    public ReservationDTO getReservationById(long id) {
        Reservation reservation = repository.findByIdOrThrow(id);
        return reservation.dto();
    }

    public void delete(long id) {
        repository.findByIdOrThrow(id);
        repository.deleteById(id);
    }

    public ReservationDTO update(ReservationDTO reservationToUpdate) {
        repository.findByIdOrThrow(reservationToUpdate.getId());
        return repository.save(reservationCreator.from(reservationToUpdate)).dto();
    }

    public ReservationDTO updateFields(long id, Map<String, Object> fieldsToUpdate) {
        Reservation reservation = repository.findByIdOrThrow(id);
        if (fieldsToUpdate.containsKey("quantity") && fieldsToUpdate.get("quantity") instanceof Integer) {
            reservation.setQuantity(Integer.class.cast(fieldsToUpdate.get("quantity")));
        }
        return repository.save(reservation).dto();
    }
}
