package action

import infrastructure.UsersRepository

class RegisterUser(private val repository : UsersRepository) {
    operator fun invoke(name: String, nickName: String) {
        repository.save(name, nickName)
    }
}