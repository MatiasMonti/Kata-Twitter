import action.FindTweets
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

class FindTweetShould {

    private lateinit var usersRepository : InMemoryUsersRepository
    private lateinit var tweetsRepository : InMemoryTweetRepository
    private lateinit var tweetService : TweetService
    private lateinit var findTweet : FindTweets
    private lateinit var tweetResult : List<Tweet>

    @BeforeEach
    internal fun setUp() {
        usersRepository = InMemoryUsersRepository()
        tweetsRepository = InMemoryTweetRepository()
        tweetService = TweetService(tweetsRepository)
        findTweet = FindTweets(tweetService)
    }

    @Test
    fun `User can tweet` (){
        givenExistingTweet(USER_1,TWEET_1)
        whenUserSearchTweets(USER_1)
        thenValidateUserTweet(TWEET_1)
    }

    private fun givenExistingTweet(user : User, tweet : Tweet) {
        usersRepository.save(user)
        tweetsRepository.save(user.nickName,tweet)
    }

    private fun whenUserSearchTweets(user : User) {
        tweetResult = findTweet(user)
    }

    private fun thenValidateUserTweet(tweet : Tweet) {
        assertEquals(tweet.tweetText, tweetResult.first().tweetText)
    }

    companion object{
        val USER_1 = UserFactory.create("Matias M.","@Kenny")
        val TWEET_1 = TweetFactory.create("This is my first tweet")
    }
}