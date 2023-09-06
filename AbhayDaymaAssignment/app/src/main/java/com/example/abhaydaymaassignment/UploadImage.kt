package com.example.abhaydaymaassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts

class UploadImage : AppCompatActivity() {

    lateinit var imgView: ImageView

    private val contract = registerForActivityResult(ActivityResultContracts.GetContent()){
        imgView.setImageURI(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_image)
        var selectImageBtn = findViewById<Button>(R.id.selectImageBtn)
        imgView = findViewById(R.id.selectedImage)
        selectImageBtn.setOnClickListener {
            contract.launch("image/*")
        }

    }
}