package my.bcit.rentright.Views.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import android.content.SharedPreferences
import android.text.TextWatcher
import my.bcit.rentright.R
import my.bcit.rentright.Utils.Validator

class Login : AppCompatActivity() {

    private lateinit var displayEmail:TextInputLayout
    private lateinit var displayPassword: TextInputLayout

    private lateinit var inputEmail: TextInputEditText
    private lateinit var inputPassword: TextInputEditText

    private lateinit var btnGoToSignUp: TextView
    private lateinit var loginButton: Button

    private lateinit var checkRememberMe: CheckBox
    private lateinit var displayForgetPassword: TextView

    val validator = Validator()
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        sharedPreferences = getSharedPreferences("RentRight", MODE_PRIVATE);
        init()
    }

    private fun init() {
        displayEmail = findViewById(R.id.displayEmail)
        displayPassword = findViewById(R.id.displayPassword)

        inputEmail = findViewById(R.id.inputEmail)
        inputPassword = findViewById(R.id.inputPassword)

        btnGoToSignUp = findViewById(R.id.btnGoToSignUp)
        loginButton = findViewById(R.id.loginButton)

        checkRememberMe = findViewById(R.id.checkRememberMe)
        displayForgetPassword = findViewById(R.id.displayForgetPassword)
    }

//    private fun readyRememberMe(){
//        checkRememberMe.
//    }

    private val emailInputWatcher:TextWatcher = object:TextWatcher {
        override 

    }
}