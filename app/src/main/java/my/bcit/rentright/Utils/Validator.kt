package my.bcit.rentright.Utils

import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Validator {

    fun verifyIsEmpty(input: TextInputEditText, display: TextInputLayout): Boolean {
        val value: String = input.text.toString().trim()
        if (value.isEmpty()) {
            display.error = "Must Not Be Empty!"
            return true
        } 
        display.error = null
        display.isErrorEnabled = false
        return false
        
    }

    fun verifyEmail (inputEmail: TextInputEditText, displayEmail: TextInputLayout): Boolean {
        val email = inputEmail.text.toString().trim()
        val regexPattern = Regex("^[A-Za-z0-9+_.-]+@(.+)\$")
        if (!regexPattern.matches(email)) {
            displayEmail.error = "Email Malformed!"
            return false
        }
        displayEmail.error = null
        displayEmail.isErrorEnabled = false
        return true
        
    }

    fun verifyPasswordEqual(inputPassword: TextInputEditText, inputConfirmPassword: TextInputEditText, displayConfirmPassword: TextInputLayout): Boolean{
        val pwd = inputPassword.text.toString().trim()
        val confirmPwd = inputConfirmPassword.text.toString().trim()

        if(pwd!=confirmPwd){
            displayConfirmPassword.error = "Passwords you entered don't match"
            return false
        }
        displayConfirmPassword.error = null
        displayConfirmPassword.isErrorEnabled = false
        return true 
        


    }

}