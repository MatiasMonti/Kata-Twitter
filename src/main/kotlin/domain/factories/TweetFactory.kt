package domain.factories

import domain.objects.Tweet


object TweetFactory {
    fun create(tweetText: String) : Tweet{
        return Tweet(tweetText.take(140))
    }
}
