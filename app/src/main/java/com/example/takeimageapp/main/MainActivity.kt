package com.example.takeimageapp.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.takeimageapp.R
import com.example.takeimageapp.images.presentation.ImagesFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.container, ImagesFragment())
            .commit()
    }
}