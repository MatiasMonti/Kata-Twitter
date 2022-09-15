package domain.services

import domain.factories.UserFactory
import domain.exceptions.ExistingUserException
import domain.repositories.UsersRepository

class UserService(private val repository : UsersRepository) {

    fun registerUser (name: String, nickName: String) {
        val user = UserFactory.create(name, nickName)
        if (user != null) {
            repository.find(nickName)?.let { throw ExistingUserException()} ?: repository.save(user)
        }
    }

    fun updateUserName (newName: String, nickName: String) {
        repository.find(nickName)?.let {
            val updatedUser = UserFactory.create(newName, nickName)
            if (updatedUser != null) {
                repository.update(updatedUser)
            }
        }
    }
}