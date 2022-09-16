package infrastructure

import domain.objects.User
import domain.repositories.UsersRepository

class InMemoryUsersRepository : UsersRepository {

    private val users = mutableListOf<User>()

    override fun save(user : User){
        users.add(user)
    }

    override fun find(nickName:String): User? {
        return users.firstOrNull() { it.nickName == nickName }
    }

    override fun update(updatedUser: User) {
        users[users.indexOf(find(updatedUser.nickName))] = updatedUser
    }
}
