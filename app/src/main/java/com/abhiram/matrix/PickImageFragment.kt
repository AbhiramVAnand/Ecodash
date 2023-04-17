package com.abhiram.matrix

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.internal.Storage
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class PickImageFragment : Fragment() {
    var imageUri : Uri? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pick_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val storageRef = Firebase.storage.reference;
        val galleryIntent = Intent(Intent.ACTION_PICK)
        // here item is type of image
        galleryIntent.type = "image/*"
        // ActivityResultLauncher callback
        imagePickerActivityResult.launch(galleryIntent)

        // Upload Task with upload to directory 'file'
        // and name of the file remains same
        val sd = getFileName(requireContext(), imageUri!!)
        val uploadTask = storageRef.child("file/$sd").putFile(imageUri!!)

        // On success, download the file URL and display it
        uploadTask.addOnSuccessListener {
            // using glide library to display the image
            Log.e("Firebase", "Upload sucesfull ")
        }.addOnFailureListener {
            Log.e("Firebase", "Image Upload fail")
        }
    }
    private var imagePickerActivityResult: ActivityResultLauncher<Intent> =
    // lambda expression to receive a result back, here we
        // receive single item(photo) on selection
        registerForActivityResult( ActivityResultContracts.StartActivityForResult()) { result ->
            if (result != null) {
                // getting URI of selected Image
                imageUri = result.data?.data

                // val fileName = imageUri?.pathSegments?.last()

                // extract the file name with extens
            }
        }

    @SuppressLint("Range")
    private fun getFileName(context: Context, uri: Uri): String? {
        if (uri.scheme == "content") {
            val cursor = context.contentResolver.query(uri, null, null, null, null)
            cursor.use {
                if (cursor != null) {
                    if(cursor.moveToFirst()) {
                        return cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                    }
                }
            }
        }
        return uri.path?.lastIndexOf('/')?.let { uri.path?.substring(it) }
    }
}