package my.bcit.rentright.ViewModels

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

import my.bcit.rentright.Network.RentRightRetrofit
import my.bcit.rentright.Utils.*
import my.bcit.rentright.Network.UserAPI
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity


class UserViewModel: ViewModel() {
    private var retrofit: Retrofit? = RentRightRetrofit.getInstance()
    private val service: UserAPI? = retrofit?.create(UserAPI::class.java)
    private val statusMessage = MutableLiveData<String>()
    private val getReady = GetReady()
    private lateinit var sharedPreferences: SharedPreferences
    fun login(email: TextInputEditText, pwd:TextInputEditText, context:Context, activity:Activity) {

        val userJson = JsonObject().apply {
            addProperty("email", email.text.toString().trim())
            addProperty("password", pwd.text.toString().trim())
        }

        service?.login(userJson)?.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.code() == 200) {
                    val body = response.body()?.toString()
                    if (!body.isNullOrEmpty()) {
                        val userData = JsonParser.parseString(body).asJsonObject

                        Log.i("DataBody",  body.toString())

                        val userName =userData.asJsonObject.get("username").asString
                        val userEmail = userData.asJsonObject.get("email").asString
                        val userPhone = userData.asJsonObject.get("phone")?.asString
                       // val userAvatar = userData.asJsonObject.get("profilePicture").asString

                        sharedPreferences = context.getSharedPreferences(
                            "Rentright",
                            AppCompatActivity.MODE_PRIVATE
                        );
                        sharedPreferences.edit().apply{
                            putString("userName", userName)
                            putString("userEmail", userEmail)
                            //putString("avatar", userAvatar)
                            putString("userPhone", userPhone)

                        }.apply()

                        CustomToast(context, "Login Successful!","GREEN").show()
                        getReady.goToHomePage(context, activity)
                        statusMessage.value = "Sign in successful"

                    }
                }
                else {
                    Log.e("code", response.code().toString())
                    Log.e("Message :",  response.body().toString())
                    CustomToast(context, "Email or password is incorrect!","RED").show()
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.e("Error on Login", t.message.toString())
                CustomToast(context, "Sorry, Something Goes Wrong!","RED").show()

            }
        })
    }

    fun register(name: TextInputEditText, email: TextInputEditText, pwd:TextInputEditText, context:Context, activity : Activity){
        val map: HashMap<String, String> = HashMap()
        map["username"] = name.text.toString()
        map["email"] = email.text.toString()
        map["password"] = pwd.text.toString()
        service?.register(map)?.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                if (response.code()==200){
                    CustomToast(context, "Success! you've joined RentRight","GREEN").show()

                    Handler(Looper.getMainLooper()).postDelayed({
                        getReady.goToHomePage(context,activity) //GoTo Page Login
                    }, 4500)
                    //
                }else{

                    Log.i("response code", response.code().toString())
                    Log.i("error body", response.body().toString())
                    CustomToast(context, "This Email Already Exist!","RED").show()
                }
            }
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                CustomToast(context, "Sorry, Something Goes Wrong!","RED").show()
/////////////////// add for now /////////////////

            }
        })
    }




}