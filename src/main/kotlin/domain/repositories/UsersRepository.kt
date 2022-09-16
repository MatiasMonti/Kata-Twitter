package domain.repositories

import domain.objects.User

interface UsersRepository {

    fun save(user: User)
    fun find(nickName:String): User?
    fun update(updatedUser: User)
}