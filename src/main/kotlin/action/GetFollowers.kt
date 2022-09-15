package action

import domain.services.FollowerService

class GetFollowers(private val followerService: FollowerService) {
    operator fun invoke(nickName: String): List<String> {
        return followerService.getFollowers(nickName)
    }
}