package domain.repositories

interface FollowersRepository {

    fun saveFollower( followerNickname : String, followedNickname : String)

    fun findFollowers(followedNickname:String): List<String>?
}