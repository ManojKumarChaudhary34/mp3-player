package com.example.mp3player

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mp3player.databinding.ActivityPlayerBinding

class PlayerActivity : AppCompatActivity() {

    companion object{
        lateinit var musicListPA: ArrayList<Music>
        var songPosition: Int=  0
        var mediaPlayer: MediaPlayer?= null
        var isPlaying: Boolean= false
    }
    private lateinit var binding: ActivityPlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        initializeLayout()
        binding.playPauseBtnPA.setOnClickListener {
            if (isPlaying){
                pauseMusic()
            }else{
                playMusic()
            }
        }
    }


//    this fun set song image and song name in the player activity
    private fun setImage(){
        //        glide is used to set images
        Glide.with(this)
            .load(musicListPA[songPosition].artUri)
            .apply(RequestOptions().placeholder(R.drawable.default_music_img))
            .into(binding.songImagePA)
        binding.songNamePA.text= musicListPA[songPosition].title
    }


//    this creates media player when a user click on songs on music adapter
    private fun createMediaPlayer(){
        try {
            if (mediaPlayer == null) mediaPlayer= MediaPlayer()
                mediaPlayer!!.reset()
                mediaPlayer!!.setDataSource(musicListPA[songPosition].path)
                mediaPlayer!!.prepare()
                mediaPlayer!!.start()
                isPlaying= true
                binding.playPauseBtnPA.setIconResource(R.drawable.baseline_pause_24)

        }catch (e:Exception){return}
    }

    private fun initializeLayout(){
        songPosition= intent.getIntExtra("index", 0)
        when(intent.getStringExtra("class")){
            "MusicAdapter" -> {
                musicListPA= ArrayList()
                musicListPA.addAll(MainActivity.MusicListMA)
                setImage()
                createMediaPlayer()
            }
        }
    }

    private fun playMusic(){
        binding.playPauseBtnPA.setIconResource(R.drawable.baseline_pause_24)
        isPlaying= true
        mediaPlayer!!.start()
    }
    private fun pauseMusic(){
        binding.playPauseBtnPA.setIconResource(R.drawable.baseline_play_arrow_24)
        isPlaying= false
        mediaPlayer!!.pause()
    }

}