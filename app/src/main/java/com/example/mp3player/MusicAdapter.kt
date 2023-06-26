package com.example.mp3player

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mp3player.databinding.EachSongBinding

class MusicAdapter(private val context: Context, private val musicList: ArrayList<Music>): RecyclerView.Adapter<MusicAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicAdapter.MyViewHolder {
        return MyViewHolder(EachSongBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: MusicAdapter.MyViewHolder, position: Int) {
        holder.title.text= musicList[position].title
        holder.album.text= musicList[position].album
        holder.duration.text= formatDuration(musicList[position].duration)
//        glide is used to set images
        Glide.with(context)
            .load(musicList[position].artUri)
            .apply(RequestOptions().placeholder(R.drawable.default_music_img))
            .into(holder.image)
//        this make to intent to player activity
        holder.root.setOnClickListener{
            val intent= Intent(context, PlayerActivity::class.java)
            intent.putExtra("index", position)
            intent.putExtra("class", "MusicAdapter")
            ContextCompat.startActivity(context, intent, null)
        }

    }

    override fun getItemCount(): Int {
        return musicList.size
    }

    class MyViewHolder(binding: EachSongBinding): RecyclerView.ViewHolder(binding.root) {
        val title = binding.songName
        val album= binding.songAlbum
        val image= binding.songImageView
        val duration= binding.songDuration
//        this is for clicking on each song so that we can move to player activity
        val root= binding.root
    }
}