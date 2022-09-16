import action.*
import infrastructure.InMemoryUsersRepository
import domain.factories.UserFactory
import domain.objects.User
import domain.repositories.FollowersRepository
import domain.repositories.UsersRepository
import domain.services.FollowerService
import infrastructure.InMemoryFollowersRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FollowUserShould {

    private lateinit var usersRepository: UsersRepository
    private lateinit var followersRepository: FollowersRepository
    private lateinit var followerService: FollowerService
    private lateinit var followUser: FollowUser
    private lateinit var result: List<String>

    @BeforeEach
    internal fun setUp() {
        usersRepository = InMemoryUsersRepository()
        followersRepository = InMemoryFollowersRepository()
        followerService = FollowerService(followersRepository)
        followUser = FollowUser(followerService)
    }

    @Test
    internal fun `follow an user`() {
        givenExistingUsers(USER_1, USER_2)
        whenFollowingUser(USER_1, USER_2)
        thenFindUserFollower(USER_2,USER_1.nickName)
    }


    private fun givenExistingUsers(user: User, userToFollow : User) {
        usersRepository.save(User(user.name, user.nickName))
        usersRepository.save(User(userToFollow.name, userToFollow.nickName))
    }

    private fun whenFollowingUser(user: User, userToFollow : User) {
        followUser(user.nickName, userToFollow.nickName)
    }

    private fun thenFindUserFollower(userFollowed: User, followerNickname : String) {
        result = followersRepository.findFollowers(userFollowed.nickName) ?: listOf()
        assertEquals(listOf(followerNickname), result)
    }

    private companion object {
        val USER_1 = UserFactory.create("Matias Sebastian", "@kenny")
        val USER_2 = UserFactory.create("Juan Manuel", "@dopa")
    }
}


