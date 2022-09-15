package domain.factories

import domain.FollowRelationship
import domain.repositories.UsersRepository

object FollowRelationshipFactory {

    fun create(followerNick: String, followedNick: String,userRepository : UsersRepository) : FollowRelationship? {
        var followRelationship : FollowRelationship? = null
        userRepository.find(followerNick)?.let {
                userRepository.find(followedNick)?.let {
                    followRelationship = FollowRelationship(followerNick, followedNick)
                }
        }
        return followRelationship
    }
}