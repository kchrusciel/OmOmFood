package pl.codecouple.omomfood.user.domain

import pl.codecouple.omomfood.user.dto.CreateUserDTO
import pl.codecouple.omomfood.user.dto.UserDTO
import pl.codecouple.omomfood.user.exceptions.UserNotFound
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

    def "Should add new user"() {
        given:
            def userToAdd = CreateUserDTO.builder()
                    .name("name")
                    .surname("surname")
                    .build()
        when:
            def result = userFacade.createUser(userToAdd)
        then:
            result.id == 1
    }

    def "Should throw UserNotFound when user not found"() {
        when:
            userFacade.getUserById(1)
        then:
            UserNotFound e = thrown()
            e.message == "User not found"
    }

    def "Should return user by id"() {
        given:
            def user = UserDTO.builder().build()
            userRepository.save(userCreator.from(user))
        when:
            def result = userFacade.getUserById(1)
        then:
            result.getId() == 1
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
            result.get(0).getId() == 1
            result.get(0).getName() == "name"
            result.get(0).getSurname() == "surname"
    }

    def "Should throw UserNotFound when delete user"() {
        when:
            userFacade.delete(1)
        then:
            UserNotFound e = thrown()
            e.message == "User not found"
    }

    def "Should delete user"() {
        given:
            def user = UserDTO.builder().build()
            userRepository.save(userCreator.from(user))
        when:
            userFacade.delete(1)
        then:
            userRepository.findById(1) == null
    }

    def "Should throw UserNotFound when update user"() {
        given:
            def user = UserDTO.builder()
                    .id(1)
                    .build()
        when:
            userFacade.update(user)
        then:
            UserNotFound e = thrown()
            e.message == "User not found"
    }

    def "Should return updated user when update user"() {
        given:
            def userToCreate = CreateUserDTO.builder()
                    .name("name")
                    .surname("surname")
                    .build()
            userFacade.createUser(userToCreate)
            def userToUpdate = UserDTO.builder()
                    .id(1)
                    .name("newName")
                    .surname("newSurname")
                    .build()
        when:
            def result = userFacade.update(userToUpdate)
        then:
            result.getId() == 1
            result.getName() == "newName"
            result.getSurname() == "newSurname"
    }

    def "Should throw UserNotFound when partial update user"() {
        when:
            userFacade.updateFields(1, new HashMap<>())
        then:
            UserNotFound e = thrown()
            e.message == "User not found"
    }

    def "Should return updated user when partial update"() {
        given:
            def userToCreate = CreateUserDTO.builder()
                    .name("name")
                    .surname("surname")
                    .build()
            userFacade.createUser(userToCreate)
            Map<String, Object> fieldsToUpdate = new HashMap<>()
            fieldsToUpdate.put("name", "newName")
        when:
            def result = userFacade.updateFields(1, fieldsToUpdate)
        then:
            result.getId() == 1
            result.getName() == "newName"
            result.getSurname() == "surname"
    }
}
