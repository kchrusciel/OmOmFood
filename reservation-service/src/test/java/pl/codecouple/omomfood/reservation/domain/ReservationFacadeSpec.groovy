package pl.codecouple.omomfood.reservation.domain

import pl.codecouple.omomfood.reservation.dto.CreateReservationDTO
import pl.codecouple.omomfood.reservation.dto.GetUserDTO
import pl.codecouple.omomfood.reservation.dto.GetOfferDTO
import pl.codecouple.omomfood.reservation.dto.ReservationDTO
import pl.codecouple.omomfood.reservation.exceptions.UserNotFound
import pl.codecouple.omomfood.reservation.exceptions.CannotCreateReservation
import pl.codecouple.omomfood.reservation.exceptions.OfferNotFound
import pl.codecouple.omomfood.reservation.exceptions.ReservationNotFound
import spock.lang.Specification
/**
 * Created by CodeCouple.pl
 */
class ReservationFacadeSpec extends Specification {

    UserService authorService = Mock()
    OfferService offerService = Mock()
    InMemoryReservationRepository repository = new InMemoryReservationRepository()
    ReservationCreator creator = new ReservationCreator()

    ReservationFacade reservationFacade = new ReservationConfiguration()
            .reservationFacade(authorService, offerService, repository)

    def "Should throw OfferNotFound when offer not found"() {
        given:
            def reservationToCreate = CreateReservationDTO.builder()
                    .userID(1)
                    .offerID(1)
                    .build()
            def getAuthor = GetUserDTO.builder()
                    .userID(1)
                    .build()
            def getOffer = GetOfferDTO.builder()
                    .offerID(1)
                    .build()
            authorService.isUserAvailable(getAuthor) >> { return true }
            offerService.isOfferAvailable(getOffer) >> { throw new OfferNotFound() }
        when:
            reservationFacade.createReservation(reservationToCreate)
        then:
            OfferNotFound e = thrown()
            e.message == "Offer not found"
    }

    def "Should throw AuthorNotFound when author not found"() {
        given:
            def reservationToCreate = CreateReservationDTO.builder()
                    .userID(1)
                    .offerID(1)
                    .build()
            def getAuthor = GetUserDTO.builder()
                    .userID(1)
                    .build()
            def getOffer = GetOfferDTO.builder()
                    .offerID(1)
                    .build()
            offerService.isOfferAvailable(getOffer) >> { return true }
            authorService.isUserAvailable(getAuthor) >> { throw new UserNotFound() }
        when:
            reservationFacade.createReservation(reservationToCreate)
        then:
            UserNotFound e = thrown()
            e.message == "User not found"
    }

    def "Should return all reservations"() {
        given:
            def reservationToCreate = CreateReservationDTO.builder()
                .userID(1)
                .offerID(1)
                .build()
            repository.save(creator.from(reservationToCreate))
        when:
            def result = reservationFacade.findAll()
        then:
            result.size() == 1
            result.get(0).getUserID() == 1
            result.get(0).getOfferID() == 1
            result.get(0).getQuantity() == 0
            result.get(0).getId() == 1
    }

    def "Should add new reservation"() {
        given:
            def reservationToCreate = CreateReservationDTO.builder()
                    .userID(10)
                    .offerID(1)
                    .build()
            def getAuthor = GetUserDTO.builder()
                    .userID(10)
                    .build()
            def getOffer = GetOfferDTO.builder()
                    .offerID(1)
                    .build()
            offerService.isOfferAvailable(getOffer) >> { return true }
            authorService.isUserAvailable(getAuthor) >> { return true }
        when:
            def result = reservationFacade.createReservation(reservationToCreate)
        then:
            result.getUserID() == 10
            result.getOfferID() == 1
            result.getQuantity() == 0
    }

    def "Should throw CannotCreateReservation exception when problems occurs" () {
        given:
            def reservationToCreate = CreateReservationDTO.builder()
                    .userID(10)
                    .offerID(1)
                    .build()
            def getOffer = GetOfferDTO.builder()
                    .offerID(1)
                    .build()
            offerService.isOfferAvailable(getOffer) >> { return false }
        when:
            reservationFacade.createReservation(reservationToCreate)
        then:
            CannotCreateReservation e = thrown()
            e.message == "Cannot create a reservation"
    }

    def "Should throw ReservationNotFound when reservation to delete not exists" () {
        when:
            reservationFacade.delete(1)
        then:
            ReservationNotFound e = thrown()
            e.message == "Reservation not found"
    }

    def "Should return reservation by id" () {
        given:
            def reservationToCreate = CreateReservationDTO.builder()
                    .userID(1)
                    .offerID(1)
                    .build()
            repository.save(creator.from(reservationToCreate))
        when:
            def result = reservationFacade.getReservationById(1)
        then:
            result.getId() == 1
    }

    def "Should delete reservation by id" () {
        given:
            def reservationToCreate = CreateReservationDTO.builder()
                    .userID(1)
                    .offerID(1)
                    .build()
            repository.save(creator.from(reservationToCreate))
        when:
            reservationFacade.delete(1)
        then:
            repository.findAll().isEmpty()
    }

    def "Should update reservation" () {
        given:
            def reservationToCreate = CreateReservationDTO.builder()
                    .userID(1)
                    .offerID(1)
                    .build()
            repository.save(creator.from(reservationToCreate))
            def reservationToUpdate = ReservationDTO.builder()
                    .id(1)
                    .userID(1)
                    .offerID(1)
                    .quantity(10)
                    .build()
        when:
            def result = reservationFacade.update(reservationToUpdate)
        then:
            result.getQuantity() == 10
    }

    def "Should partial update reservation" () {
        given:
            def reservationToCreate = CreateReservationDTO.builder()
                    .userID(1)
                    .offerID(1)
                    .quantity(10)
                    .build()
            repository.save(creator.from(reservationToCreate))
            Map<String, Object> fieldsToUpdate = new HashMap<>()
            fieldsToUpdate.put("quantity", 15)
        when:
            def result = reservationFacade.updateFields(1, fieldsToUpdate)
        then:
            result.getQuantity() == 15
    }
}