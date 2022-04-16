package com.example.broadcast.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.example.broadcast.R
import com.example.broadcast.firebase.FireStoreClass

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler().postDelayed({

            var currentUserId = FireStoreClass().getCurrentUserID()

//            if(currentUserId.isNotEmpty()){
//                startActivity(Intent(this, MainActivity::class.java))
//                finish()
//            }
//            else{
                startActivity(Intent(this, IntroActivity::class.java))
                finish()
            //}

        },2000)
    }
}