package action

import domain.services.FollowerService

class FollowUser(private val followerService: FollowerService) {
    operator fun invoke(userNickName: String, UserToFollowNickName: String) {
        followerService.followUser(userNickName, UserToFollowNickName)
    }
}