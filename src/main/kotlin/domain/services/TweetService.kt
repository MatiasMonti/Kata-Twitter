package domain.services

import domain.objects.Tweet
import domain.factories.TweetFactory
import domain.repositories.TweetRepository

class TweetService(private val repository: TweetRepository) {

    fun publishTweet(nickName: String, tweetText: String) {
        val tweet = TweetFactory.create(tweetText)
        repository.save(nickName, tweet)
    }

    fun findTweets(nickName: String) : List<Tweet>{
        return repository.find(nickName)?: listOf()
    }
}
