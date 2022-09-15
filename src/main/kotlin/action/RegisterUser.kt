package action

import domain.services.UserService

class RegisterUser(private val userService: UserService) {
    operator fun invoke(name: String, nickName: String) {
        userService.registerUser(name,nickName)
    }
}