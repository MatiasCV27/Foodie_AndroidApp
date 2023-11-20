package com.idat.foodie_app.NavbarFragment

import android.app.Activity
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
import com.idat.foodie_app.R
import com.idat.foodie_app.UseerAccess.AyudaActivity
import com.idat.foodie_app.UseerAccess.FaqActivity
import com.idat.foodie_app.UseerAccess.HistorialActivity

class AccountFragment : Fragment() {

    private val PICK_IMAGE_REQUEST = 1
    private val TAKE_PHOTO_REQUEST = 2
    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var imageView2: ImageView

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
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        floatingActionButton = view.findViewById(R.id.floatingActionButton)
        imageView2 = view.findViewById(R.id.imageView2)

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imageView2 = view?.findViewById(R.id.imageView2)!!

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                PICK_IMAGE_REQUEST -> {
                    if (data != null && data.data != null) {
                        val imageUri = data.data
                        imageView2.setImageURI(imageUri)
                    }
                }
                TAKE_PHOTO_REQUEST -> {
                    val imageBitmap = data?.extras?.get("data") as Bitmap?
                    if (imageBitmap != null) {
                        imageView2.setImageBitmap(imageBitmap)
                    }
                }
            }
        }
    }
}