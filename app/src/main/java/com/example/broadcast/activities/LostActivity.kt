package com.example.broadcast.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.broadcast.R
import kotlinx.android.synthetic.main.activity_lost.*
import kotlinx.android.synthetic.main.activity_signin.*
import kotlinx.android.synthetic.main.activity_signin.toolbar_sign_in_activity

class LostActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lost)
        setActionBar()
        fab_add_lost.setOnClickListener{
            startActivity(Intent(this,AddLostActivity::class.java))
        }
    }

    private fun setActionBar(){
        setSupportActionBar(toolbar_lost_activity)
        val actionBar = supportActionBar

        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }

        toolbar_lost_activity.setNavigationOnClickListener{onBackPressed()}


    }
}