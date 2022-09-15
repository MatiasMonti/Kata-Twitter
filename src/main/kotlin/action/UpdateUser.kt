package action

import domain.services.UserService

class UpdateUser(private val userService: UserService) {
    operator fun invoke(updatedName: String, nickName: String) {
        userService.updateUserName(updatedName,nickName)
    }
}