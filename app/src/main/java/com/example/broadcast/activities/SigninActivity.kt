package com.example.broadcast.activities

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.example.broadcast.R
import com.example.broadcast.firebase.FireStoreClass
import com.example.broadcast.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_signin.*
import kotlinx.android.synthetic.main.activity_signin.et_signin_email
import kotlinx.android.synthetic.main.activity_signin.et_signin_password
import kotlinx.android.synthetic.main.activity_signup.*

class SigninActivity : BaseActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        auth = Firebase.auth
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setActionBar()

        btn_sign_in.setOnClickListener{
            signinRegisteredUser()
        }
    }

    private fun setActionBar(){
        setSupportActionBar(toolbar_sign_in_activity)
        val actionBar = supportActionBar

        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }

        toolbar_sign_in_activity.setNavigationOnClickListener{onBackPressed()}


    }



    private fun  signinRegisteredUser(){
        val email: String = et_signin_email.text.toString().trim { it <= ' ' }
        val password: String = et_signin_password.text.toString().trim { it <= ' ' }

        if(validateSigninForm(email,password)){
            showProgressDialog("Please Wait")

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->

                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("signing", "signInWithEmail:success")
                        val user = auth.currentUser
                        FireStoreClass().signinUser(this)

                    } else {
                        // If sign in fails, display a message to the user.
                        hideProgressDialog()
                        Log.w("signing", "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()

                    }
                }
        }
    }


    private fun validateSigninForm(email: String, password: String): Boolean {
        return when {
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

    fun signinSuccess(user: User) {
        hideProgressDialog()
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }


}