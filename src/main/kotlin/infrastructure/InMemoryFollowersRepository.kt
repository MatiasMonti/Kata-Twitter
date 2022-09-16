package infrastructure

import domain.repositories.FollowersRepository

class InMemoryFollowersRepository : FollowersRepository {

    private val followersMap = mutableMapOf<String, MutableList<String>>()


    override fun saveFollower( followerNickname : String, followedNickname : String) {
        val updatedFollowerList = followersMap[followerNickname]?: mutableListOf()
        updatedFollowerList.add(followerNickname)
        followersMap[followedNickname] = updatedFollowerList
    }

    override fun findFollowers(followedNickname: String): List<String>? {
        return followersMap[followedNickname]?.toList()
    }


}
