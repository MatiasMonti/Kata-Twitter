package domain.exceptions


class ExistingUserException : Throwable() {
    override val message = "User Nickname Already Exist"
}