package infrastructure

import domain.User

object InMemoryUsersRepository {

    private val users = mutableListOf<User>()

    fun save(name:String, nickname: String){
        users.add(User(name,nickname))
    }

    fun find(nickName:String): User {
        return users.first { it.nickName == nickName }
    }
}
