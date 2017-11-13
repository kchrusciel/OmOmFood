package pl.codecouple.omomfood.reservation.domain

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import pl.codecouple.omomfood.reservation.dto.AuthorDTO
import pl.codecouple.omomfood.reservation.dto.GetAuthorDTO
import pl.codecouple.omomfood.reservation.exceptions.AuthorNotFound
import spock.lang.Specification
/**
 * Created by CodeCouple.pl
 */
class AuthorServiceSpec extends Specification {

    RestTemplate template = Mock()
    AuthorService service = new AuthorService(template)

    def "Should throw 'Author not found' exception when author ID not exists"() {
        given:
            def author = GetAuthorDTO.builder().authorID(1).build()
            template.getForEntity(
                    ServiceAddress.AUTHOR_SERVICE_URI + "/{authorID}",
                    AuthorDTO.class, author.getAuthorID()) >> { throw new AuthorNotFound() }
        when:
            service.getAuthor(author)
        then:
            AuthorNotFound e = thrown()
            e.message == "Author not found"
    }

    def "Should throw 'Author not found' when status from user-service is different than 200"(){
        given:
            def author = GetAuthorDTO.builder().authorID(1).build()
            template.getForEntity(
                    ServiceAddress.AUTHOR_SERVICE_URI + "/{authorID}",
                    AuthorDTO.class, author.getAuthorID()) >> { return new ResponseEntity<GetAuthorDTO>(HttpStatus.NOT_FOUND) }
        when:
            service.getAuthor(author)
        then:
            AuthorNotFound e = thrown()
            e.message == "Author not found"
    }

    def "Should return author id when author exists in user-service" () {
        given:
            def getAuthor = GetAuthorDTO.builder().authorID(2).build()
            def author = AuthorDTO.builder().authorID(2).build()
            template.getForEntity(
                    ServiceAddress.AUTHOR_SERVICE_URI + "/{authorID}",
                    AuthorDTO.class, getAuthor.getAuthorID()) >> { return new ResponseEntity<AuthorDTO>(author, HttpStatus.OK) }
        when:
            def result = service.getAuthor(getAuthor)
        then:
            result.getAuthorID() == 2
    }

    def "Should return true when author is available in user-service" () {
        given:
            def getAuthor = GetAuthorDTO.builder().authorID(2).build()
            def author = AuthorDTO.builder().authorID(2).build()
            template.getForEntity(
                    ServiceAddress.AUTHOR_SERVICE_URI + "/{authorID}",
                    AuthorDTO.class, getAuthor.getAuthorID()) >> { return new ResponseEntity<AuthorDTO>(author, HttpStatus.OK) }
        when:
            def result = service.isAuthorAvailable(getAuthor)
        then:
            result
    }

}