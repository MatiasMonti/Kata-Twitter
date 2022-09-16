package action

import domain.objects.User
import domain.services.TweetService

class PublishTweet(private val tweetService: TweetService) {
    operator fun invoke(user: User, tweetText: String){
        tweetService.publishTweet(user.nickName,tweetText)
    }
}
