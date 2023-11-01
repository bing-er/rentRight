package my.bcit.rentright.ViewModels

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import my.bcit.rentright.Data.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

//import tn.yassin.discovery.Network.UserApi
import my.bcit.rentright.Network.RentRightRetrofit
import my.bcit.rentright.R
import my.bcit.rentright.Utils.*
import my.bcit.rentright.Network.UserAPI



class UserViewModel {
    private var retrofit: Retrofit? = RentRightRetrofit.getInstance()
    private val service: UserAPI? = retrofit?.create(UserAPI::class.java)

    var UserLiveData: MutableLiveData<UserData> = MutableLiveData()

    private val statusMessage = MutableLiveData<String>()

    fun login(email: TextInputEditText, pwd:TextInputEditText, context : Context, view : View) {
        //Block Touch To prevent user click to many on btn login (Too much Request of Login)
        //ReadyFunction.BlockTouch(Activity)
        //
        val userJson = JsonObject().apply {
            addProperty("email", email.text.toString().trim())
            addProperty("password", pwd.text.toString().trim())
        }

        service?.login(userJson)?.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.code() == 200) {
                    var body = response.body()?.toString()
                    if (!body.isNullOrEmpty()) {
                        var userData = JsonParser.parseString(body).asJsonObject

                        println("DataBody "+body)
                        println("UserData "+userData)

//                        val userName =userData.asJsonObject.get("username").asString
//                        val userEmail = userData.asJsonObject.get("email").asString
//                        val userPhone = userData.asJsonObject.get("phone").asString
//                        val userAvatar = userData.asJsonObject.get("profilePicture").asString
//
//                        MySharedPref = context.getSharedPreferences(
//                            PREF_NAME,
//                            AppCompatActivity.MODE_PRIVATE
//                        );
//                        MySharedPref.edit().apply{
//                            putString(IDUSER,id)
//                            putString(NAMEUSER, userName)
//                            putString(EMAILUSER, userEmail)
//                            putString(BIOUSER, userBio)
//                            putString(AVATARUSER, userAvatar)
//                            putString(TOKENUSER,token)
//                            putString(ROLEUSER,role)
//                            if(cbRememberMe.isChecked) {
//                                putString(RememberEmail, loginemail.text.toString().trim())
//                                putString(RememberPassword, loginpassw.text.toString().trim())
//                            }
//                        }.apply()

                        CustomToast(context, "Login Successful!","GREEN").show()
//                        sessionManager.saveAuthToken(userData.asJsonObject.get("token").asString)
                        ReadyFunction.GoToActivityNavigation(context,Activity) //GoTo Page Navigation
                        statusMessage.value = "Sign in successful"

                    }
                }
                else {
//                    ReadyFunction().hideView(view) //Hide ProgressBar
                    Log.e("RETROFIT_ERROR", response.code().toString())
                    println("Message :" + response.errorBody()?.string())
                    CustomToast(context, "Email or password is incorrect!","RED").show()
                    statusMessage.value = "please enter all fields"
                    //Enable touch Again
                    // ReadyFunction.ActiveTouch(Activity)
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
//                ReadyFunction().hideView(view) //Hide ProgressBar
                Log.e("Error", t.message.toString())
                CustomToast(context, "Sorry, Something Goes Wrong!","RED").show()
            }
        })
    }

    fun Register(txtname: TextInputEditText,txtemail: TextInputEditText,txtpass:TextInputEditText,context:Context,Activity : Activity){
        val map: HashMap<String, String> = HashMap()
        map["username"] = txtname.text.toString()
        map["email"] = txtemail.text.toString()
        map["password"] = txtpass.text.toString()
        service.signup(map).enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                //
                val Body = response.body().toString()
                var json = JsonParser()
                var Data = json.parse(Body).asJsonObject
                //val message =Data!!.asJsonObject.get("message").asString
                //
                if (response.code()==202){
                    CustomToast(context, "Success! you've joined Discovery","GREEN").show()
                    SendConfirmEmail(txtemail.text.toString()) // Methode Post To Send Verification Email Of User Need to Confirm
                    //
                    val factory = LayoutInflater.from(context)
                    val view: View = factory.inflate(R.layout.dialogverifyemail, null)
                    val msg = CustomDialog()
                    msg.ShowCustomDialog(context, view)
                    //
                    Handler().postDelayed({
                        ReadyFunction.GoToActivityLogin(context,Activity) //GoTo Page Login
                    }, 4500)
                    //
                }else{
                    CustomToast(context, "This Email Already Exist!","RED").show()
                }
            }
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                CustomToast(context, "Sorry, Something Goes Wrong!","RED").show()
            }
        })
    }


}