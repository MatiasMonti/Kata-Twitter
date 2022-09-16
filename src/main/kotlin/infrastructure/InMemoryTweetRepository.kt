package infrastructure

import domain.objects.Tweet
import domain.repositories.TweetRepository

class InMemoryTweetRepository : TweetRepository {

    private val repository = mutableMapOf<String,MutableList<Tweet>>()

    override fun save(nickName: String, tweet: Tweet) {
        val newTweetList = repository[nickName] ?: mutableListOf()
        newTweetList.add(tweet)
        repository[nickName] = newTweetList
    }

    override fun find(nickName: String): List<Tweet>? {
        return repository[nickName]?.toList()
    }
}