package application

import infrastructure.InMemoryUsersRepository

class RegisterUser {
    operator fun invoke(name: String, nickName: String) {
        InMemoryUsersRepository.save(name, nickName)
    }
}