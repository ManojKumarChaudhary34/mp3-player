package com.example.mp3player

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class PlaylistActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlist)
        supportActionBar?.hide()
    }
}