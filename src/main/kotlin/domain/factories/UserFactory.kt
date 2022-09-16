package domain.factories

import domain.objects.User

object UserFactory {

    fun create(name : String, nickname : String) : User {
        return if(nickname[0] == '@') User(name,nickname) else throw ExceptionInInitializerError()
    }
}