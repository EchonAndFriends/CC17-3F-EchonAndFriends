package com.example.swiftstudy.Main
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.swiftstudy.Auth.Login
import com.example.swiftstudy.Auth.SignUp

class Start : AppCompatActivity() {

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
