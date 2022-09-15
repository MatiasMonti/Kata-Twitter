package domain.services

import domain.factories.FollowRelationshipFactory
import domain.repositories.FollowersRepository
import domain.repositories.UsersRepository

class FollowerService(private val followerRepository : FollowersRepository,private val userRepository : UsersRepository) {

    fun followUser(userNickName: String, userToFollowNickName: String) {

        val followRelationShip = FollowRelationshipFactory.create(userNickName,userToFollowNickName,userRepository)
        if(!followerRepository.findFollowers(userToFollowNickName).contains(followRelationShip)){
            followRelationShip?.let { followerRepository.saveFollower(it) }
        }
    }

    fun getFollowers(nickName: String): List<String> {
        return followerRepository.findFollowers(nickName).map { it.followerNick}
    }
}