import infrastructure.InMemoryUsersRepository
import action.RegisterUser
import domain.User
import domain.exceptions.ExistingUserException
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RegisterUserShould {

    @Test
    internal fun `Register an user`() {
        //given

        //when
        val repository = InMemoryUsersRepository()
        val registerUser = RegisterUser(repository)
        registerUser(USER_1.name, USER_1.nickName)

        //then
        val result = repository.find(USER_1.nickName)
        assertEquals(USER_1,result)
    }

    @Test
    internal fun `register another user`() {
        //given

        //when
        val repository = InMemoryUsersRepository()
        val registerUser = RegisterUser(repository)
        registerUser(USER_2.name, USER_2.nickName)

        //then
        val result = repository.find(USER_2.nickName)
        assertEquals(USER_2,result)
    }
    @Test
    internal fun `register existing user nickname`() {
        //given
        var result  = ""

        //when
        val registerUser = RegisterUser(InMemoryUsersRepository())

        registerUser(USER_2.name, USER_2.nickName)

        try{
            registerUser(USER_2.name, USER_2.nickName)
        }catch (e : Throwable){
            result = e.message.toString()
        }

        //then
        assertEquals(ExistingUserException().message,result)
    }


    private companion object {
        val USER_1 = User("Matias Sebastian", "@kenny")
        val USER_2 = User("Juan Manuel", "@dopa")
    }
}


