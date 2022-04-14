package com.example.broadcast.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.broadcast.R
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        btn_signup_intro.setOnClickListener{
            startActivity(Intent(this, SignupActivity::class.java))

        }

        btn_signin_intro.setOnClickListener{
            startActivity(Intent(this, SigninActivity::class.java))

        }
    }
}