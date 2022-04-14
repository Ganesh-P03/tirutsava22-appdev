package com.example.broadcast.firebase

import android.util.Log
import com.example.broadcast.activities.SigninActivity
import com.example.broadcast.activities.SignupActivity
import com.example.broadcast.models.User
import com.google.firebase.auth.FirebaseAuth
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

}
