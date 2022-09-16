package action

import domain.objects.Tweet
import domain.objects.User
import domain.services.TweetService

class FindTweets(private val tweetService: TweetService){

    operator  fun invoke(user: User): List<Tweet>{
        return tweetService.findTweets(user.nickName)
    }
}
