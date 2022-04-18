package com.example.broadcast.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.broadcast.R
import com.example.broadcast.firebase.FireStoreClass
import com.example.broadcast.models.Lost
import com.example.broadcast.utils.Constants
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_add_lost.*
import kotlinx.android.synthetic.main.activity_lost.*
import java.io.IOException

class AddLostActivity : BaseActivity() {

    private var mSelectedImageFileUri: Uri? = null
    private var mBoardImageURL: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_lost)


        iv_add_img.setOnClickListener { view ->

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED
            ) {
                Constants.showImageChooser(this)
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    Constants.READ_STORAGE_PERMISSION_CODE
                )
            }
        }

        btn_create.setOnClickListener {

            // Here if the image is not selected then update the other details of user.
            if (mSelectedImageFileUri != null) {

                uploadLostImage()
            } else {

                showProgressDialog("Please Wait")

                // Call a function to update create a board.
                createLost()
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.READ_STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Constants.showImageChooser(this)
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(
                    this,
                    "Oops, you just denied the permission for storage. You can also allow it from settings.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK
            && requestCode == Constants.PICK_IMAGE_REQUEST_CODE
            && data!!.data != null
        ) {
            mSelectedImageFileUri = data.data

            try {
                // Load the board image in the ImageView.
                Glide
                    .with(this)
                    .load(Uri.parse(mSelectedImageFileUri.toString())) // URI of the image
                    .centerCrop() // Scale type of the image.
                    .placeholder(R.drawable.ic_img_launcher_background) // A default place holder
                    .into(iv_add_img) // the view in which the image will be loaded.
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun uploadLostImage() {
        showProgressDialog("Please Wait")

        //getting the storage reference
        val sRef: StorageReference = FirebaseStorage.getInstance().reference.child(
            "LOST_IMAGE" + System.currentTimeMillis() + "."
                    + Constants.getFileExtension(this, mSelectedImageFileUri)
        )

        //adding the file to reference
        sRef.putFile(mSelectedImageFileUri!!)
            .addOnSuccessListener { taskSnapshot ->
                // The image upload is success
                Log.e(
                    "Firebase Image URL",
                    taskSnapshot.metadata!!.reference!!.downloadUrl.toString()
                )

                // Get the downloadable url from the task snapshot
                taskSnapshot.metadata!!.reference!!.downloadUrl
                    .addOnSuccessListener { uri ->
                        Log.e("Downloadable Image URL", uri.toString())

                        // assign the image url to the variable.
                        mBoardImageURL = uri.toString()
                        createLost()
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(
                            this,
                            mBoardImageURL,
                            Toast.LENGTH_LONG
                        ).show()

                        hideProgressDialog()
                    }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(
                    this,
                    mSelectedImageFileUri.toString(),
                    Toast.LENGTH_LONG
                ).show()

                hideProgressDialog()
            }
    }


    private fun createLost() {



        // Creating the instance of the Board and adding the values as per parameters.
        val lost = Lost(

            mBoardImageURL,
            et_lost_name.text.toString(),
            et_lost_desc.text.toString(),
            et_lost_time.text.toString(),
            et_lost_place.text.toString(),
            et_lost_contact_num.text.toString().toLong()
        )

        FireStoreClass().createLost(this, lost)
    }


    fun lostCreatedSuccessfully() {

        hideProgressDialog()

        setResult(Activity.RESULT_OK)
        finish()
    }



}