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

class GetFollowersShould {

    private lateinit var usersRepository: UsersRepository
    private lateinit var followersRepository: FollowersRepository
    private lateinit var followerService: FollowerService
    private lateinit var getFollowers: GetFollowers
    private lateinit var result: List<String>

    @BeforeEach
    internal fun setUp() {
        usersRepository = InMemoryUsersRepository()
        followersRepository = InMemoryFollowersRepository()
        followerService = FollowerService(followersRepository)
        getFollowers = GetFollowers(followerService)

    }
    @Test
    internal fun `get user follower`() {
        givenExistingFollowingUser(USER_1, USER_2)
        whenAskingForUserFollowers(USER_2)
        thenCheckUserHasFollower(USER_1.nickName)
    }



    private fun givenExistingFollowingUser(user: User, userToFollow : User) {
        usersRepository.save(User(user.name, user.nickName))
        usersRepository.save(User(userToFollow.name, userToFollow.nickName))
        followersRepository.saveFollower(user.nickName, userToFollow.nickName)
    }

    private fun whenAskingForUserFollowers(userToFollowed : User) {
        result = getFollowers(userToFollowed.nickName)
    }

    private fun thenCheckUserHasFollower(followerNickname : String) {
        assertEquals(listOf(followerNickname), result)
    }

    private companion object {
        val USER_1 = UserFactory.create("Matias Sebastian", "@kenny")
        val USER_2 = UserFactory.create("Juan Manuel", "@dopa")
    }
}


