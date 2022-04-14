package com.example.broadcast.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.broadcast.R
import kotlinx.android.synthetic.main.activity_events.*
import kotlinx.android.synthetic.main.activity_lost.*
import kotlinx.android.synthetic.main.activity_lost.toolbar_lost_activity

class EventsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)
        setActionBar()
        fab_add_events.setOnClickListener{
            startActivity(Intent(this,AddEventsActivity::class.java))
        }
    }

    private fun setActionBar(){
        setSupportActionBar(toolbar_events_activity)
        val actionBar = supportActionBar

        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }

        toolbar_events_activity.setNavigationOnClickListener{onBackPressed()}


    }
}