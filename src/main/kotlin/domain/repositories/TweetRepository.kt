package domain.repositories

import domain.objects.Tweet

interface TweetRepository {
    fun save(nickName: String, tweet: Tweet)
    fun find(nickName: String): List<Tweet>?
}
