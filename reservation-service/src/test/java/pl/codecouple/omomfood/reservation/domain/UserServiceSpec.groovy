package pl.codecouple.omomfood.reservation.domain

import org.springframework.http.*
import pl.codecouple.omomfood.reservation.dto.GetUserDTO
import pl.codecouple.omomfood.reservation.dto.UserDTO
import pl.codecouple.omomfood.reservation.exceptions.UserNotFound
import spock.lang.Specification
/**
 * Created by CodeCouple.pl
 */
class UserServiceSpec extends Specification {

    UserServiceGetter getter = Mock()
    UserService service = new UserService(getter)

    def "Should throw 'User not found' exception when author ID not exists"() {
        given:
            def user = GetUserDTO.builder().userID(1).build()
            getter.getUser(user) >> { throw new UserNotFound() }
        when:
            service.getUser(user)
        then:
            UserNotFound e = thrown()
            e.message == "User not found"
    }

    def "Should throw 'User not found' when status from user-service is different than 200"(){
        given:
            def user = GetUserDTO.builder().userID(1).build()
            getter.getUser(user) >> { return new ResponseEntity<GetUserDTO>(HttpStatus.NOT_FOUND) }
        when:
            service.getUser(user)
        then:
            UserNotFound e = thrown()
            e.message == "User not found"
    }

    def "Should return user id when user exists in user-service" () {
        given:
            def getUser = GetUserDTO.builder().userID(2).build()
            def user = UserDTO.builder().id(2).build()
            getter.getUser(getUser) >> { return new ResponseEntity<UserDTO>(user, HttpStatus.OK) }
        when:
            def result = service.getUser(getUser)
        then:
            result.getId() == 2
    }

    def "Should return true when user is available in user-service" () {
        given:
            def getUser = GetUserDTO.builder().userID(2).build()
            def user = UserDTO.builder().id(2).build()
            getter.getUser(getUser) >> { return new ResponseEntity<UserDTO>(user, HttpStatus.OK) }
        when:
            def result = service.isUserAvailable(getUser)
        then:
            result
    }

}