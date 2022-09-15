import action.*
import infrastructure.InMemoryUsersRepository
import domain.factories.UserFactory
import domain.services.UserService
import domain.exceptions.ExistingUserException
import domain.services.FollowerService
import infrastructure.InMemoryFollowersRepository
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RegisterUserShould {

    @Test
    internal fun `Register an user`() {
        //given
        val repository = InMemoryUsersRepository()
        val userService = UserService(repository)

        //when
        val registerUser = RegisterUser(userService)
        registerUser(USER_1.name, USER_1.nickName)

        //then
        val result = repository.find(USER_1.nickName)
        assertEquals(USER_1,result)
    }

    @Test
    internal fun `register another user`() {
        //given
        val repository = InMemoryUsersRepository()
        val userService = UserService(repository)

        //when
        val registerUser = RegisterUser(userService)
        registerUser(USER_2.name, USER_2.nickName)

        //then
        val result = repository.find(USER_2.nickName)
        assertEquals(USER_2,result)
    }
    @Test
    internal fun `register existing user nickname`() {
        //given
        var result  = ""
        val repository = InMemoryUsersRepository()
        val userService = UserService(repository)

        //when
        val registerUser = RegisterUser(userService)
        registerUser(USER_2.name, USER_2.nickName)

        try{
            registerUser(USER_2.name, USER_2.nickName)
        }catch (e : Throwable){
            result = e.message.toString()
        }
        //then
        assertEquals(ExistingUserException().message,result)
    }
    @Test
    internal fun `update an existing User`() {
        //given
        val repository = InMemoryUsersRepository()
        val userService = UserService(repository)

        //when
        val registerUser = RegisterUser(userService)
        val updateUser = UpdateUser(userService)
        registerUser(USER_2.name, USER_2.nickName)
        updateUser(USER_2_UPDATED_NAME, USER_2.nickName)

        //then
        val result = repository.find(USER_2.nickName)!!.name
        assertEquals(USER_2_UPDATED_NAME, result)
    }
    @Test
    internal fun `follow an user and get followers list`() {
        //given
        val usersRepository = InMemoryUsersRepository()
        val userService = UserService(usersRepository)
        val followerServiceService = FollowerService(InMemoryFollowersRepository(),usersRepository)

        //when
        val registerUser = RegisterUser(userService)
        val followUser = FollowUser(followerServiceService)
        val getFollowers = GetFollowers(followerServiceService)
        registerUser(USER_2.name, USER_2.nickName)
        registerUser(USER_1.name, USER_1.nickName)
        followUser(USER_1.nickName,USER_2.nickName)

        //then
        val result = getFollowers(USER_2.nickName)
        assertEquals(listOf(USER_1.nickName), result)
    }
    @Test
    internal fun `get followers for user without followers`() {
        //given
        val usersRepository = InMemoryUsersRepository()
        val userService = UserService(usersRepository)
        val followerServiceService = FollowerService(InMemoryFollowersRepository(),usersRepository)

        //when
        val registerUser = RegisterUser(userService)
        val getFollowers = GetFollowers(followerServiceService)
        registerUser(USER_2.name, USER_2.nickName)

        //then
        val result = getFollowers(USER_2.nickName)
        assertEquals(listOf(), result)
    }
    @Test
    internal fun `user can follow other use only once`() {
        //given
        val usersRepository = InMemoryUsersRepository()
        val userService = UserService(usersRepository)
        val followerServiceService = FollowerService(InMemoryFollowersRepository(),usersRepository)

        //when
        val registerUser = RegisterUser(userService)
        val followUser = FollowUser(followerServiceService)
        val getFollowers = GetFollowers(followerServiceService)
        registerUser(USER_2.name, USER_2.nickName)
        registerUser(USER_1.name, USER_1.nickName)
        followUser(USER_1.nickName,USER_2.nickName)
        followUser(USER_1.nickName,USER_2.nickName)

        //then
        val result = getFollowers(USER_2.nickName)
        assertEquals(listOf(USER_1.nickName), result)
    }
    @Test
    internal fun `only users can follow`() {
        //given
        val usersRepository = InMemoryUsersRepository()
        val userService = UserService(usersRepository)
        val followerServiceService = FollowerService(InMemoryFollowersRepository(),usersRepository)

        //when
        val registerUser = RegisterUser(userService)
        val followUser = FollowUser(followerServiceService)
        val getFollowers = GetFollowers(followerServiceService)
        registerUser(USER_2.name, USER_2.nickName)
        followUser(USER_2_UPDATED_NAME,USER_2.nickName)

        //then
        val result = getFollowers(USER_2.nickName)
        assertEquals(listOf(), result)
    }

    private companion object {
        val USER_1 = UserFactory.create("Matias Sebastian", "@kenny")!!
        val USER_2 = UserFactory.create("Juan Manuel", "@dopa")!!
        const val USER_2_UPDATED_NAME = "Juan M."
    }
}


