package my.bcit.rentright.Views.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import my.bcit.rentright.R
import my.bcit.rentright.Utils.Validator

class Signup : AppCompatActivity() {

    private lateinit var displayName: TextInputLayout
    private lateinit var displayEmail: TextInputLayout
    private lateinit var displayPassword: TextInputLayout
    private lateinit var displayConfirmPassword: TextInputLayout

    private lateinit var inputName: TextInputEditText
    private lateinit var inputEmail: TextInputEditText
    private lateinit var inputPassword: TextInputEditText
    private lateinit var inputConfirmPassword: TextInputEditText

    private lateinit var registerButton: Button
    private lateinit var loginButton: TextView


    var validator = Validator()

////only checks the filed for now without creating the user ////
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
//        val spinner: Spinner = findViewById(R.id.userRole)
//        val adapter = ArrayAdapter.createFromResource(
//            this,
//            R.array.user_type, android.R.layout.simple_spinner_item
//        )
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spinner.adapter = adapter

    }

    fun init() {

        displayName = findViewById(R.id.displayName)
        displayEmail = findViewById(R.id.displayEmail)
        displayPassword = findViewById(R.id.displayPassword)
        displayConfirmPassword = findViewById(R.id.displayConfirmPassword)

        inputName = findViewById(R.id.inputName)
        inputEmail = findViewById(R.id.inputEmail)
        inputPassword = findViewById(R.id.inputPassword)
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword)

        registerButton= findViewById(R.id.registerButton)
        loginButton = findViewById(R.id.loginButton)




    }

    private val NametxtWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {
            Validator.VerifisEmpty(txtName,txtNameLayout)
        }
    }
    private val EmailtxtWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {
            Validator.VerifisEmpty(txtEmail,txtEmailLayout)
        }
    }
    private val PasswordtxtWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {
            Validator.VerifisEmpty(txtPwd,txtPasswordLayout)
        }
    }
    private val PasswordConfirmtxtWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {
            Validator.VerifisEmpty(txtConfirmPwd,txtConfirmPasswordLayout)
        }
    }
}