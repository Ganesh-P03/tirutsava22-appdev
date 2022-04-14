package com.example.broadcast.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.broadcast.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_events.setOnClickListener{
            startActivity(Intent(this,EventsActivity::class.java))
        }

        tv_lost.setOnClickListener{
            startActivity(Intent(this,LostActivity::class.java))
        }

        tv_found.setOnClickListener{
            startActivity(Intent(this,FoundActivity::class.java))
        }
    }
}