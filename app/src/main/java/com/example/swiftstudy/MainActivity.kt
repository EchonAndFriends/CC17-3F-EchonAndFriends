package com.example.swiftstudy
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import com.example.swiftstudy.Login
import com.example.swiftstudy.SignUp

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.startup)

        val button1 = findViewById<Button>(R.id.loginButton)
        button1.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        val button2 = findViewById<Button>(R.id.signUpButton)
        button2.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }
}
