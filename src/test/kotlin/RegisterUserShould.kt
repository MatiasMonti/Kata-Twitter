import infrastructure.InMemoryUsersRepository
import application.RegisterUser
import domain.User
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RegisterUserShould {

    @Test
    internal fun `Register an user`() {
        //given

        //when
        val registerUser = RegisterUser()
        registerUser(USER_1.name, USER_1.nickName)

        //then
        val repository = InMemoryUsersRepository
        val result = repository.find(USER_1.nickName)
        assertEquals(USER_1,result)
    }

    @Test
    internal fun `register another user`() {
        //given

        //when
        val registerUser = RegisterUser()
        registerUser(USER_2.name, USER_2.nickName)

        //then
        val repository = InMemoryUsersRepository
        val result = repository.find(USER_2.nickName)
        assertEquals(USER_2,result)
    }

    private companion object {
        val USER_1 = User("Matias Sebastian", "@kenny")
        val USER_2 = User("Juan Manuel", "@dopa")
    }
}


