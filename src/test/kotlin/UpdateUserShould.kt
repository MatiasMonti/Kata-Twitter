import action.*
import infrastructure.InMemoryUsersRepository
import domain.factories.UserFactory
import domain.services.UserService
import domain.objects.User
import domain.repositories.UsersRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UpdateUserShould {

    private lateinit var repository: UsersRepository
    private lateinit var userService: UserService
    private lateinit var updateUser: UpdateUser
    private lateinit var result: String

    @BeforeEach
    internal fun setUp() {
        repository = InMemoryUsersRepository()
        userService = UserService(repository)
        updateUser = UpdateUser(userService)

    }

    @Test
    internal fun `update an existing User`() {
        givenRegisteredUser(USER_2)
        whenUpdateUser(USER_2,USER_2_UPDATED_NAME)
        thenGivenFinalNameResult(USER_2,USER_2_UPDATED_NAME)
    }

    @Test
    internal fun `update another existing User`() {
        givenRegisteredUser(USER_1)
        whenUpdateUser(USER_1,USER_1_UPDATED_NAME)
        thenGivenFinalNameResult(USER_1,USER_1_UPDATED_NAME)
    }

    private fun givenRegisteredUser(user : User) {
        repository.save(user)
    }

    private fun whenUpdateUser(user : User, updatedName : String) {
        updateUser(updatedName, user.nickName)
    }

    private fun thenGivenFinalNameResult(user : User, expectedName : String) {
        result = repository.find(user.nickName)?.name ?: ""
        assertEquals(expectedName, result)
    }

    private companion object {
        val USER_1 = UserFactory.create("Matias Sebastian", "@kenny")
        val USER_2 = UserFactory.create("Juan Manuel", "@dopa")
        const val USER_1_UPDATED_NAME = "Matias Monti"
        const val USER_2_UPDATED_NAME = "Juan M."
    }
}


