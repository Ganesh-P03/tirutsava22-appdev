package com.example.broadcast.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.broadcast.R
import com.example.broadcast.firebase.FireStoreClass
import com.example.broadcast.models.Event
import com.example.broadcast.models.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_add_events.*
import kotlinx.android.synthetic.main.activity_signup.*

class AddEventsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_events)

        btn_create_event.setOnClickListener{
                registerEvent()
        }

    }

    private fun registerEvent(){

        val name: String = et_event_name.text.toString().trim { it <= ' ' }
        val description: String = et_description.text.toString().trim { it <= ' ' }
        val eventTime: String = et_event_time.text.toString().trim { it <= ' ' }
        val type="Technical"

        if(validateEvent(name,description,eventTime)){

            showProgressDialog("Please Wait")
            val curr_event =Event(name,description,eventTime,type);
            FireStoreClass().addNewEvent(this,curr_event)
        }

    }

    private fun validateEvent(name: String, email: String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(name) -> {
                showErrorSnackBar("Please enter name.")
                false
            }
            TextUtils.isEmpty(email) -> {
                showErrorSnackBar("Please enter email.")
                false
            }
            TextUtils.isEmpty(password) -> {
                showErrorSnackBar("Please enter password.")
                false
            }
            else -> {
                true
            }
        }
    }


    fun eventRegisteredSuccess() {
        hideProgressDialog()
        Toast.makeText(this,"Event Registered Success",Toast.LENGTH_SHORT).show()
        startActivity(Intent(this,EventsActivity::class.java))
        finish()
    }
}