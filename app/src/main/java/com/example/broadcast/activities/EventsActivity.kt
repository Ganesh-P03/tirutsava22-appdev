package com.example.broadcast.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.broadcast.R
import com.example.broadcast.adapters.EventItemsAdapter
import com.example.broadcast.firebase.FireStoreClass
import com.example.broadcast.models.Event
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

        showProgressDialog("Please Wait")
        FireStoreClass().getEventsList(this)

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

//    fun populateEventsListToUI(eventList:ArrayList<Event>){
//        hideProgressDialog()
//
//        if(eventList.size>0){
//            rv_events_list.visibility= View.VISIBLE
//            tv_no_events_available.visibility=View.GONE
//
//            rv_events_list.layoutManager =LinearLayoutManager(this)
//            rv_events_list.setHasFixedSize(true)
//
//            val adapter = EventItemsAdapter(this@MainActivity, eventList)
//            rv_events_list.adapter = adapter // Attach the adapter to the recyclerView.
//
//            adapter.setOnClickListener(object :
//                EventItemsAdapter.OnClickListener {
//                override fun onClick(position: Int, model: Board) {
//                    val intent = Intent(this@MainActivity, TaskListActivity::class.java)
//                    intent.putExtra(Constants.DOCUMENT_ID, model.documentId)
//                    startActivity(intent)
//                }
//            })
//        } else {
//            rv_events_list.visibility = View.GONE
//            tv_no_events_available.visibility = View.VISIBLE
//
//        }
//    }

}