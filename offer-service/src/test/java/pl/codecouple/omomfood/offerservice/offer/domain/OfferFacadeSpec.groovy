package pl.codecouple.omomfood.offerservice.offer.domain

import pl.codecouple.omomfood.offerservice.offer.dto.CreateOfferDto
import pl.codecouple.omomfood.offerservice.offer.dto.GetAuthorDto
import pl.codecouple.omomfood.offerservice.offer.dto.OfferDto
import pl.codecouple.omomfood.offerservice.offer.exceptions.AuthorNotFound
import pl.codecouple.omomfood.offerservice.offer.exceptions.OfferNotFound
import spock.lang.Specification

/**
 * Created by CodeCouple.pl
 */
class OfferFacadeSpec extends Specification {

    AuthorService authorService = Mock()
    InMemoryOfferRepository offerRepository = new InMemoryOfferRepository()
    OfferFacade offerFacade = new OfferConfiguration().offerFacade(offerRepository, authorService)
    OfferCreator offerCreator = new OfferCreator()

    def "Should throw AuthorNotFound when author not found"() {
        given:
            def offerToCreate = CreateOfferDto.builder()
                    .authorId(1)
                    .build()
            def getAuthor = GetAuthorDto.builder()
                    .authorId(1)
                    .build()
            authorService.getAuthor(getAuthor) >> { throw new AuthorNotFound() }
        when:
            offerFacade.createOffer(offerToCreate)
        then:
            AuthorNotFound e = thrown()
            e.message == "Author not found"
    }

    def "Should add new offer"() {
        given:
            def offerToCreate = CreateOfferDto.builder()
                    .authorId(10)
                    .title("title")
                    .content("content")
                    .build()
        when:
            def result = offerFacade.createOffer(offerToCreate)
        then:
            result.getId() == 1
            result.getAuthorId() == 10
            result.getTitle() == "title"
            result.getContent() == "content"
    }

    def "Should return all offers"() {
        given:
            def offerToCreate = CreateOfferDto.builder()
                    .authorId(10)
                    .title("title")
                    .content("content")
                    .build()
            offerRepository.save(offerCreator.from(offerToCreate))
        when:
            def result = offerFacade.findAll()
        then:
            result.size() == 1
            result.get(0).getId() == 1
            result.get(0).getAuthorId() == 10
            result.get(0).getTitle() == "title"
            result.get(0).getContent() == "content"
    }

    def "Should throw OfferNotFound when offer not found"() {
        when:
            offerFacade.findById(1)
        then:
            OfferNotFound e = thrown()
            e.message == "Offer not found"
    }

    def "Should return offer by id"() {
        given:
            def offer = OfferDto.builder().build()
            offerRepository.save(offerCreator.from(offer))
        when:
            def result = offerFacade.findById(1)
        then:
            result.getId() == 1
    }

    def "Should throw OfferNotFound when delete offer"() {
        when:
            offerFacade.delete(1)
        then:
            OfferNotFound e = thrown()
            e.message == "Offer not found"
    }

    def "Should delete offer"() {
        given:
            def offer = OfferDto.builder().build()
            offerRepository.save(offerCreator.from(offer))
        when:
            offerFacade.delete(1)
        then:
            offerRepository.findById(1) == null
    }

    def "Should throw OfferNotFound when update offer"() {
        given:
            def offer = OfferDto.builder()
                    .id(1)
                    .build()
        when:
            offerFacade.update(offer)
        then:
            OfferNotFound e = thrown()
            e.message == "Offer not found"
    }

    def "Should return updated offer when update offer"() {
        given:
            def offerToCreate = CreateOfferDto.builder()
                    .title("Makbet")
                    .build()
            offerFacade.createOffer(offerToCreate)
            def offerToUpdate = OfferDto.builder()
                    .id(1)
                    .title("title")
                    .content("content")
                    .build()
        when:
            def result = offerFacade.update(offerToUpdate)
        then:
            result.getId() == 1
            result.getTitle() == "title"
            result.getContent() == "content"
    }

    def "Should throw OfferNotFound when partial update offer"() {
        when:
            offerFacade.updateFields(1, new HashMap<>())
        then:
            OfferNotFound e = thrown()
            e.message == "Offer not found"
    }

    def "Should return updated offer when partial update"() {
        given:
            def offerToCreate = CreateOfferDto.builder()
                    .title("title")
                    .build()
            offerFacade.createOffer(offerToCreate)
            Map<String, Object> fieldsToUpdate = new HashMap<>()
            fieldsToUpdate.put("content", "content")
        when:
            def result = offerFacade.updateFields(1, fieldsToUpdate)
        then:
            result.getId() == 1
            result.getTitle() == "title"
            result.getContent() == "content"
    }
}