package pl.codecouple.omomfood.offerservice.offer.domain

import pl.codecouple.omomfood.offerservice.offer.dto.CreateOfferDto
import pl.codecouple.omomfood.offerservice.offer.dto.GetAuthorDto
import pl.codecouple.omomfood.offerservice.offer.exceptions.AuthorNotFound
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
}