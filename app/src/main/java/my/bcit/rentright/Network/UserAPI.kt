package my.bcit.rentright.Network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*
import java.util.*
import kotlin.collections.HashMap

interface UserAPI {
    @POST("user/login")
    fun login(@Body data:JsonObject): Call<JsonObject>

    @POST("user/logout")
    fun logout(@Body user:JsonObject): Call<JsonObject>


    @POST("user/register")
    fun register(@Body data:HashMap<String, String>): Call<JsonObject>


    @GET("user/current")
    fun getCurrent(): Call<JsonObject>



    @PATCH("user/current")
    fun updateUser(@Body data:HashMap<String, String>): Call<JsonObject>

}