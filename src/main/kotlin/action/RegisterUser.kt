package action

import domain.exceptions.ExistingUserException
import infrastructure.UsersRepository

class RegisterUser(private val repository : UsersRepository) {
    operator fun invoke(name: String, nickName: String) {
        if(repository.exist(nickName)){
            throw ExistingUserException()
        }
        else repository.save(name, nickName)
    }
}