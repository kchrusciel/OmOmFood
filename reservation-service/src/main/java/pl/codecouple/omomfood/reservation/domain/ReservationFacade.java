package pl.codecouple.omomfood.reservation.domain;

import pl.codecouple.omomfood.reservation.dto.CreateReservationDTO;
import pl.codecouple.omomfood.reservation.dto.GetAuthorDTO;
import pl.codecouple.omomfood.reservation.dto.GetOfferDTO;
import pl.codecouple.omomfood.reservation.dto.ReservationDTO;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by CodeCouple.pl
 */
public class ReservationFacade {

    private final AuthorService authorService;
    private final OfferService offerService;
    private final AuthorCreator authorCreator;
    private final OfferCreator offerCreator;
    private final ReservationRepository repository;
    private final ReservationCreator reservationCreator;

    public ReservationFacade(AuthorService authorService,
                             OfferService offerService,
                             AuthorCreator authorCreator,
                             OfferCreator offerCreator,
                             ReservationRepository repository, ReservationCreator reservationCreator) {
        this.authorService = authorService;
        this.offerService = offerService;
        this.authorCreator = authorCreator;
        this.offerCreator = offerCreator;
        this.repository = repository;
        this.reservationCreator = reservationCreator;
    }

    public ReservationDTO createReservation(CreateReservationDTO reservationToCreate) {
        GetOfferDTO getOfferDTO = offerCreator.from(reservationToCreate);
        offerService.getOffer(getOfferDTO);
        GetAuthorDTO getAuthorDTO = authorCreator.from(reservationToCreate);
        authorService.getAuthor(getAuthorDTO);
        return repository.save(reservationCreator.from(reservationToCreate)).dto();
    }

    public List<ReservationDTO> findAll() {
        return repository.findAll().stream()
                .map(Reservation::dto)
                .collect(toList());
    }


}
