package my.bcit.rentright.Data

class UserData {

    internal var username: String? = null
    private var email: String? = null
    private var phone: String? = null
    private var  profilePicture: String? = null



    fun getName(): String? {
        return username
    }

    fun setName(newName: String?) {
        username = newName
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(newEmail: String?) {
        email = newEmail
    }


    fun getPassword(): String? {
        return phone
    }

    fun setPassword(newPassword: String?) {
        phone = newPassword
    }
    // Getter avatar
    fun getAvatar(): String? {
        return profilePicture
    }

    fun setAvatar(newAvatar: String?) {
        profilePicture = newAvatar
    }


}
