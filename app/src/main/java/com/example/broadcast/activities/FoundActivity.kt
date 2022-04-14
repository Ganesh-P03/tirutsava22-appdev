package com.example.broadcast.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.broadcast.R
import kotlinx.android.synthetic.main.activity_found.*

class FoundActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_found)
        setActionBar()
    }

    private fun setActionBar(){
        setSupportActionBar(toolbar_found_activity)
        val actionBar = supportActionBar

        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }

        toolbar_found_activity.setNavigationOnClickListener{onBackPressed()}


    }
}