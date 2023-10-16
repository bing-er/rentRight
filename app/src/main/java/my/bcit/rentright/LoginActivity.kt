package my.bcit.rentright

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val spinner: Spinner = findViewById(R.id.userRole)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.user_type, android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val button:Button = findViewById(R.id.register_button)

        button.setOnClickListener{
            val intent = Intent(this, HomePageActivity::class.java )
            startActivity(intent)
        }
    }
}