import action.*
import infrastructure.InMemoryUsersRepository
import domain.factories.UserFactory
import domain.services.UserService
import domain.exceptions.ExistingUserException
import domain.objects.User
import domain.repositories.UsersRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RegisterUserShould {

    private lateinit var repository: UsersRepository
    private lateinit var userService: UserService
    private lateinit var registerUser: RegisterUser
    private lateinit var result: String

    @BeforeEach
    internal fun setUp() {
        repository = InMemoryUsersRepository()
        userService = UserService(repository)
        registerUser = RegisterUser(userService)

    }
    @Test
    internal fun `Register an user`() {
        whenRegister(USER_1)
        thenUserIsFound(USER_1)
    }
    @Test
    internal fun `register another user`() {
        whenRegister(USER_2)
        thenUserIsFound(USER_2)
    }

    @Test
    internal fun `register existing user nickname`() {

        givenRegisteredUser(USER_2)
        whenRegisterExistingUser(USER_2)
        thenExceptionIsThrew(ExistingUserException())
    }

    private fun thenExceptionIsThrew(exception: Throwable) {
        assertEquals(exception.message, result)
    }

    private fun givenRegisteredUser(user : User) {
        repository.save(user)
    }

    private fun whenRegister(user: User) {
        registerUser(user.name, user.nickName)
    }

    private fun whenRegisterExistingUser(user : User) {
        try {
            registerUser(user.name, user.nickName)
        } catch (e: Throwable) {
            result = e.message.toString()
        }
    }

    private fun thenUserIsFound(user: User) {
        val result = repository.find(user.nickName)
        assertEquals(user, result)
    }

    private companion object {
        val USER_1 = UserFactory.create("Matias Sebastian", "@kenny")
        val USER_2 = UserFactory.create("Juan Manuel", "@dopa")
    }
}


