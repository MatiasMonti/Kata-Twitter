package infrastructure

import domain.User

class InMemoryUsersRepository : UsersRepository {

    private val users = mutableListOf<User>()

    override fun save(name:String, nickname: String){
        users.add(User(name,nickname))
    }

    override fun find(nickName:String): User {

        return users.first { it.nickName == nickName }
    }

    override fun exist(nickName: String): Boolean {
        return users.map { it.nickName }.contains(nickName)
    }
}
