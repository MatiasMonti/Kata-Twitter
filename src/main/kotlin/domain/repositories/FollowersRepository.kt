package domain.repositories

import domain.FollowRelationship

interface FollowersRepository {

    fun saveFollower(followRelationship : FollowRelationship)

    fun findFollowers(nickName:String): List<FollowRelationship>
}