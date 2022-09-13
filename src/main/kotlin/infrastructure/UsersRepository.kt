package infrastructure

import domain.User

interface UsersRepository {

    fun save(name:String, nickname: String)

    fun find(nickName:String): User
    fun exist(nickName: String): Boolean
}