package my.bcit.rentright.Models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("username") var userName: String,
    @SerializedName("email") var userEmail: String,
    @SerializedName("profilePicture") val userAvatar: String,
    @SerializedName("token") val token:String,
) :  Serializable

