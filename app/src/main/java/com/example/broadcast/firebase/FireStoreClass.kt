package com.example.broadcast.firebase

import android.util.Log
import android.widget.Toast
import com.example.broadcast.activities.*
import com.example.broadcast.models.Event
import com.example.broadcast.models.Lost
import com.example.broadcast.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.ktx.Firebase

class FireStoreClass {

    private val myFirestore = FirebaseFirestore.getInstance()

    fun registerUser(activity: SignupActivity, userInfo: User){
        myFirestore.collection("USERS")
            .document(getCurrentUserID())

            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegisteredSuccess()
            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error writing document",
                    e
                )
            }
    }

    fun signinUser(activity:SigninActivity){
        myFirestore.collection("USERS")
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener {document->
                val loggedinUser= document.toObject(User::class.java)

                if(loggedinUser!=null){
                    activity.signinSuccess(loggedinUser)
                }

            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error getting data  document",
                    e
                )
            }
    }


    fun getCurrentUserID(): String {
        val currentUser = FirebaseAuth.getInstance().currentUser

        var currentUserID = ""
        if (currentUser != null) {
            currentUserID = currentUser.uid
        }
        return currentUserID
    }

    fun addNewEvent(activity: AddEventsActivity, eventInfo: Event){

        myFirestore.collection("EVENTS")
            .document()
            .set(eventInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.eventRegisteredSuccess()
            }
            .addOnFailureListener { e ->
                //activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error writing document",
                    e
                )
            }
    }



    fun getEventsList(activity:EventsActivity){
        myFirestore.collection("EVENTS")
            .get()
            .addOnSuccessListener { document ->

                Log.e(activity.javaClass.simpleName, document.documents.toString())

                val eventsList: ArrayList<Event> = ArrayList()


                for (i in document.documents) {

                    val event = i.toObject(Event::class.java)!!


                    eventsList.add(event)
                }


                activity.populateEventsListToUI(eventsList)
            }
            .addOnFailureListener { e ->

                activity.hideProgressDialog()
                Log.e(activity.javaClass.simpleName, "Error while creating a event.", e)
            }


    }



    fun createLost(activity: AddLostActivity, lost: Lost) {

        myFirestore.collection("LOSTS")
            .document()
            .set(lost, SetOptions.merge())
            .addOnSuccessListener {
                Log.e(activity.javaClass.simpleName, "Lost created successfully.")

                Toast.makeText(activity, "Your Lost item is Added", Toast.LENGTH_SHORT).show()

                activity.lostCreatedSuccessfully()
            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while adding a lost.",
                    e
                )
            }
    }


    fun getLostsList(activity:LostActivity){
        myFirestore.collection("LOSTS")
            .get()
            .addOnSuccessListener { document ->

                Log.e(activity.javaClass.simpleName, document.documents.toString())

                val lostsList: ArrayList<Lost> = ArrayList()


                for (i in document.documents) {

                    val lost = i.toObject(Lost::class.java)!!


                    lostsList.add(lost)
                }


                activity.populateLostsListToUI(lostsList)
            }
            .addOnFailureListener { e ->

                activity.hideProgressDialog()
                Log.e(activity.javaClass.simpleName, "Error while addingg a lost.", e)
            }


    }






}
