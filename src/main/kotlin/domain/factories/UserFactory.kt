package domain.factories

import domain.User

object UserFactory {

    fun create(name : String, nickname : String) : User? {
        return if(nickname[0] == '@') User(name,nickname) else null
    }
}