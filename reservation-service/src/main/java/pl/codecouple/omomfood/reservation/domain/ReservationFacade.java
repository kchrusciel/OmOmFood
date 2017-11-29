package pl.codecouple.omomfood.reservation.domain;

import pl.codecouple.omomfood.reservation.dto.*;
import pl.codecouple.omomfood.reservation.exceptions.CannotCreateReservation;
import pl.codecouple.omomfood.reservation.exceptions.FulfilledReservation;

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
        if (fieldsToUpdate.containsKey("assignedUsers") && fieldsToUpdate.get("assignedUsers") instanceof List) {
            List<Integer> assignedUsers = List.class.cast(fieldsToUpdate.get("assignedUsers"));
            OfferDTO offer = offerService.getOffer((GetOfferDTO.builder().offerID(id).build()));
            if (offer.getQuantity() < assignedUsers.size()) {
                throw new FulfilledReservation(String.format("Reservation with ID: %d is fulfilled", id));
            }
            reservation.setAssignedUsers(assignedUsers);
        }
        return repository.save(reservation).dto();
    }
}
