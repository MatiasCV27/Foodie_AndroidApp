package com.idat.foodie_app.NavbarFragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.idat.foodie_app.LoginActivity
import com.idat.foodie_app.R
import com.idat.foodie_app.UseerAccess.AyudaActivity
import com.idat.foodie_app.UseerAccess.FaqActivity
import com.idat.foodie_app.UseerAccess.HistorialActivity
import java.io.ByteArrayOutputStream
//...//
import com.bumptech.glide.Glide

class AccountFragment : Fragment() {

    private val PICK_IMAGE_REQUEST = 1
    private val TAKE_PHOTO_REQUEST = 2
    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var imageView2: ImageView
    private lateinit var storageReference: StorageReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_account, container, false)

        view.findViewById<Button>(R.id.btnHistorial).setOnClickListener {
            val intent = Intent(activity, HistorialActivity::class.java)
            startActivity(intent)
        }
        view.findViewById<Button>(R.id.btnFaq).setOnClickListener {
            val intent = Intent(activity, FaqActivity::class.java)
            startActivity(intent)
        }
        view.findViewById<Button>(R.id.btnAyuda).setOnClickListener {
            val intent = Intent(activity, AyudaActivity::class.java)
            startActivity(intent)
        }
        view.findViewById<Button>(R.id.btnCerrarSesion).setOnClickListener {

            val context = requireActivity()
            val prefs = context.getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()

            FirebaseAuth.getInstance().signOut()

            val intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        floatingActionButton = view.findViewById(R.id.floatingActionButton)
        imageView2 = view.findViewById(R.id.imageView2)
        storageReference = FirebaseStorage.getInstance("gs://android-foodie-bf103.appspot.com").reference
        val prefs = requireActivity().getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val imageUrl = prefs.getString("profileImageUrl", null)

        floatingActionButton.setOnClickListener {
            showImageSourceDialog()
        }
        if (imageUrl != null) {
            Glide.with(this).load(imageUrl).into(imageView2)
        }

        floatingActionButton.setOnClickListener {
            showImageSourceDialog()
        }
    }

    private fun showImageSourceDialog() {
        val options = arrayOf("Cámara", "Galería")

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Seleccionar Fuente de Imagen")
            .setItems(options) { dialog, which ->
                when (which) {
                    0 -> takePhoto()
                    1 -> pickImageFromGallery()
                }
                dialog.dismiss()
            }
            .show()
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    private fun takePhoto() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(requireActivity().packageManager) != null) {
            startActivityForResult(takePictureIntent, TAKE_PHOTO_REQUEST)
        }
    }

    private fun updateProfileImageUrl(uid: String, imageUrl: String) {
        val databaseReference = FirebaseDatabase.getInstance().reference
        val userRef = databaseReference.child("users").child(uid)
        userRef.child("profileImageUrl").setValue(imageUrl)

        val prefs = requireActivity().getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("profileImageUrl", imageUrl)
        prefs.apply()
    }

    private fun uploadImageToStorage(imageBitmap: Bitmap) {
        val uid = Firebase.auth.currentUser?.uid
        if (uid != null) {
            val imageRef = storageReference.child("profile_images/$uid.jpg")

            val baos = ByteArrayOutputStream()
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()

            val uploadTask: UploadTask = imageRef.putBytes(data)

            uploadTask.addOnSuccessListener {
                // Imagen subida con éxito, obtén la URL de descarga
                imageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                    // Ahora, 'downloadUri' contiene la URL de descarga de la imagen
                    // Puedes almacenar esta URL en Realtime Database
                    val imageUrl = downloadUri.toString()
                    updateProfileImageUrl(uid, imageUrl)
                }
            }.addOnFailureListener { exception ->
                // Manejar el error
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imageView2 = view?.findViewById(R.id.imageView2)!!

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                PICK_IMAGE_REQUEST -> {
                    if (data != null && data.data != null) {
                        val imageUri = data.data
                        imageView2.setImageURI(imageUri)

                        val imageBitmap = MediaStore.Images.Media.getBitmap(
                            requireActivity().contentResolver,
                            imageUri
                        )

                        uploadImageToStorage(imageBitmap)
                    }
                }
                TAKE_PHOTO_REQUEST -> {
                    val imageBitmap = data?.extras?.get("data") as Bitmap?
                    if (imageBitmap != null) {
                        imageView2.setImageBitmap(imageBitmap)

                        uploadImageToStorage(imageBitmap)
                    }
                }
            }
        }
    }
}