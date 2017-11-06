package pl.codecouple.omomfood.reservation.domain

import pl.codecouple.omomfood.reservation.dto.CreateReservationDTO
import pl.codecouple.omomfood.reservation.dto.GetAuthorDTO
import pl.codecouple.omomfood.reservation.dto.GetOfferDTO
import pl.codecouple.omomfood.reservation.exceptions.AuthorNotFound
import pl.codecouple.omomfood.reservation.exceptions.OfferNotFound
import spock.lang.Specification
/**
 * Created by CodeCouple.pl
 */
class ReservationFacadeSpec extends Specification {

    AuthorService authorService = Mock()
    OfferService offerService = Mock()
    InMemoryReservationRepository repository = new InMemoryReservationRepository()
    ReservationCreator creator = new ReservationCreator()

    ReservationFacade reservationFacade = new ReservationConfiguration()
            .reservationFacade(authorService, offerService, repository)

    def "Should throw OfferNotFound when offer not found"() {
        given:
            def reservationToCreate = CreateReservationDTO.builder()
                    .authorID(1)
                    .offerID(1)
                    .build()
            def getOffer = GetOfferDTO.builder()
                    .offerID(1)
                    .build()
            offerService.getOffer(getOffer) >> { throw new OfferNotFound() }
        when:
            reservationFacade.createReservation(reservationToCreate)
        then:
            OfferNotFound e = thrown()
            e.message == "Offer not found"
    }

    def "Should throw AuthorNotFound when author not found"() {
        given:
            def reservationToCreate = CreateReservationDTO.builder()
                    .authorID(1)
                    .offerID(1)
                    .build()
            def getAuthor = GetAuthorDTO.builder()
                    .authorID(1)
                    .build()
            authorService.getAuthor(getAuthor) >> { throw new AuthorNotFound() }
        when:
            reservationFacade.createReservation(reservationToCreate)
        then:
            AuthorNotFound e = thrown()
            e.message == "Author not found"
    }

    def "Should return all reservations"() {
        given:
            def reservationToCreate = CreateReservationDTO.builder()
                .authorID(1)
                .offerID(1)
                .build()
            repository.save(creator.from(reservationToCreate))
        when:
            def result = reservationFacade.findAll()
        then:
            result.size() == 1
            result.get(0).getAuthorID() == 1
            result.get(0).getOfferID() == 1
            result.get(0).getQuantity() == 0
            result.get(0).getReservationID() == 1
    }

    def "Should add new reservation"() {
        given:
            def reservationToCreate = CreateReservationDTO.builder()
                    .authorID(10)
                    .offerID(1)
                    .build()
        when:
            def result = reservationFacade.createReservation(reservationToCreate)
        then:
            result.getAuthorID() == 10
            result.getOfferID() == 1
            result.getQuantity() == 0
    }

}