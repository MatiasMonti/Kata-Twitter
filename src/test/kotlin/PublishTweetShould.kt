import action.PublishTweet
import domain.factories.TweetFactory
import domain.factories.UserFactory
import domain.objects.Tweet
import domain.objects.User
import domain.services.TweetService
import infrastructure.InMemoryTweetRepository
import infrastructure.InMemoryUsersRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PublishTweetShould {

    private lateinit var usersRepository : InMemoryUsersRepository
    private lateinit var tweetsRepository : InMemoryTweetRepository
    private lateinit var tweetService : TweetService
    private lateinit var publishTweet : PublishTweet
    private lateinit var tweetResult : List<Tweet>

    @BeforeEach
    internal fun setUp() {
        usersRepository = InMemoryUsersRepository()
        tweetsRepository = InMemoryTweetRepository()
        tweetService = TweetService(tweetsRepository)
        publishTweet = PublishTweet(tweetService)
    }

    @Test
    fun `User can tweet` (){
        givenExistingUser(USER_1)
        whenUserPublishTweet(USER_1, TWEET_1)
        thenValidateUserTweet(USER_1,TWEET_1 )
    }

    private fun thenValidateUserTweet(user : User, tweet : Tweet) {
        tweetResult = tweetsRepository.find(user.nickName) ?: listOf()
        assertEquals(tweet.tweetText, tweetResult.first().tweetText)
    }

    private fun whenUserPublishTweet(user : User, tweet : Tweet) {
        publishTweet(user, tweet.tweetText)
    }

    private fun givenExistingUser(user : User) {
        usersRepository.save(user)
    }

    companion object{
        val USER_1 = UserFactory.create("Matias M.","@Kenny")
        val TWEET_1 = TweetFactory.create("This is my first tweet")
    }

}