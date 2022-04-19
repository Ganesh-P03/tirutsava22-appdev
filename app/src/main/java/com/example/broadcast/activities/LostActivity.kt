package com.example.broadcast.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.broadcast.R
import com.example.broadcast.adapters.EventItemsAdapter
import com.example.broadcast.adapters.LostItemsAdapter
import com.example.broadcast.firebase.FireStoreClass
import com.example.broadcast.models.Event
import com.example.broadcast.models.Lost
import kotlinx.android.synthetic.main.activity_events.*
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

        showProgressDialog("Please Wait")
        FireStoreClass().getLostsList(this)
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


    fun populateLostsListToUI(lostList:ArrayList<Lost>){
        hideProgressDialog()

        if(lostList.size>0){
            rv_losts_list.visibility= View.VISIBLE
            tv_nothing_available.visibility= View.GONE

            rv_losts_list.layoutManager = LinearLayoutManager(this)
            rv_losts_list.setHasFixedSize(true)

            val adapter = LostItemsAdapter(this, lostList)
            rv_losts_list.adapter = adapter // Attach the adapter to the recyclerView.

        } else {
            rv_losts_list.visibility = View.GONE
            tv_nothing_available.visibility = View.VISIBLE

        }
    }
}