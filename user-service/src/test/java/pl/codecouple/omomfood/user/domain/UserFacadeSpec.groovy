package pl.codecouple.omomfood.user.domain

import pl.codecouple.omomfood.user.dto.CreateUserDTO
import spock.lang.Specification

/**
 * Created by CodeCouple.pl
 */
class UserFacadeSpec extends Specification {

    def userRepository
    def userCreator
    def userFacade

    def setup() {
        userCreator = new UserCreator()
        userRepository = new InMemoryUserRepository()
        userFacade = new UserFacade(userRepository, userCreator)
    }

    def "Should return all users"() {
        given:
            def userToSave = User.builder()
                    .name("name")
                    .surname("surname")
                    .build()
            userRepository.save(userToSave)
        when:
            def result = userFacade.findAll()
        then:
            result.size() == 1
    }

    def "Should add new user"() {
        given:
            def userToAdd = CreateUserDTO.builder()
                    .name("name")
                    .surname("surname")
                    .build()
        when:
            def result = userFacade.save(userToAdd)
        then:
            result.id == 1
    }

    def "Should return user by id"() {
        given:
            def userToSave = User.builder()
                    .name("name")
                    .surname("surname")
                    .build()
            userRepository.save(userToSave)
        when:
            def result = userFacade.getUserById(1)
        then:
            result.id == 1
            result.surname == "surname"
    }

    def "Should delete user by id"() {

    }

    def "Should update user"() {

    }

    def "Should partial update user"() {

    }

}
