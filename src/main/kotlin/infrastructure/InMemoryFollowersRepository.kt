package infrastructure

import domain.FollowRelationship
import domain.repositories.FollowersRepository

class InMemoryFollowersRepository : FollowersRepository {

    private val followersList = mutableSetOf<FollowRelationship>()


    override fun saveFollower( followRelationship : FollowRelationship) {
        followersList.add(followRelationship)
    }

    override fun findFollowers(nickName: String): List<FollowRelationship> {
        return followersList.sortedBy { it.followedNick == nickName }
    }


}
