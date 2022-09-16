package domain.services

import domain.repositories.FollowersRepository

class FollowerService(private val followedRepository : FollowersRepository) {

    fun followUser(userNickName: String, userToFollowNickName: String) {
        followedRepository.saveFollower(userNickName,userToFollowNickName)
    }

    fun getFollowers(nickName: String): List<String> {
        return followedRepository.findFollowers(nickName)?: listOf()
    }
}