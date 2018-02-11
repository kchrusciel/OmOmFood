package pl.codecouple.omomfood.user

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import pl.codecouple.omomfood.user.domain.UserFacade
import pl.codecouple.omomfood.user.dto.CreateUserDTO
import pl.codecouple.omomfood.user.dto.UserDTO
import pl.codecouple.omomfood.user.exceptions.UserNotFound
import spock.lang.Specification

import static org.mockito.Mockito.doNothing
import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * Created by CodeCouple.pl
 */
@WebMvcTest(UserController)
class UserControllerSpec extends Specification {

    @Autowired
    MockMvc user
    @MockBean
    UserFacade facade
    ObjectMapper mapper = new ObjectMapper()

    def "Should return new user when user is created"() {
        given:
            def createdUser = UserDTO.builder()
                    .id(1)
                    .username("username")
                    .name("name")
                    .surname("surname")
                    .build()
            def userToCreate = CreateUserDTO.builder()
                    .username("username")
                    .name("name")
                    .surname("surname")
                    .build()
            when(facade.createUser(userToCreate)).thenReturn(createdUser)
        when:
            def result = user.perform(post("/users")
                    .content(mapper.writeValueAsString(userToCreate))
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
        then:
            result.andExpect(content().string(mapper.writeValueAsString(createdUser)))
                    .andExpect(status().isCreated())
    }

    def "Should return list of all users when get '/users'"() {
        given:
            def userFromDb = UserDTO.builder()
                    .id(1)
                    .username("username")
                    .name('name')
                    .surname("surname")
                    .build()
            def users = Collections.singletonList(userFromDb)
            when(facade.findAll()).thenReturn(users)
        when:
            def result = user.perform(get("/users"))
        then:
            result.andExpect(content().json(mapper.writeValueAsString(users)))
                    .andExpect(status().isOk())
    }

    def "Should return user by ID when get '/users/{id}'" () {
        given:
            def userFromDb = UserDTO.builder()
                    .id(1)
                    .username("username")
                    .name('name')
                    .surname("surname")
                    .build()
            when(facade.getUserById(1)).thenReturn(userFromDb)
        when:
            def result = user.perform(get("/users/1"))
        then:
            result.andExpect(content().json(mapper.writeValueAsString(userFromDb)))
                    .andExpect(status().isOk())
    }

    def "Should throw 'User not found' when update user which not exists"() {
        given:
            def userToUpdate = UserDTO.builder()
                    .id(0)
                    .build()
            when(facade.update(userToUpdate)).thenThrow(UserNotFound)
        when:
            def result = user.perform(put("/users")
                    .content(mapper.writeValueAsString(userToUpdate))
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
        then:
            result.andExpect(status().isNotFound())
                    .andExpect(status().reason("User not found"))
    }

    def "should return updated user when update user"() {
        given:
            def userToUpdate = UserDTO.builder()
                    .id(1)
                    .build()
            when(facade.update(userToUpdate)).thenReturn(userToUpdate)
        when:
            def result = user.perform(put("/users")
                    .content(mapper.writeValueAsString(userToUpdate))
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
        then:
            result.andExpect(content().json(mapper.writeValueAsString(userToUpdate)))
                    .andExpect(status().isNoContent())
    }

    def "should return updated user when partial update user"() {
        given:
            def userToUpdate = UserDTO.builder()
                    .id(1)
                    .build()
            when(facade.updateFields(1, new HashMap<String, Object>())).thenReturn(userToUpdate)
        when:
            def result = user.perform(patch("/users/1")
                    .content(mapper.writeValueAsString(new HashMap<String, Object>()))
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
        then:
            result.andExpect(content().json(mapper.writeValueAsString(userToUpdate)))
                    .andExpect(status().isNoContent())
    }

    def "should return 'User deleted' when user is deleted"() {
        given:
            doNothing().when(facade).delete(1)
        when:
            def result = user.perform(delete("/users/1"))
        then:
            result.andExpect(status().isNoContent())
    }
}