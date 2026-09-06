package com.example.videoadsearning

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class VideoAdapter(
    private val videos: List<Video>,
    private val onVideoClick: (Video) -> Unit
) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val thumbnailImageView: ImageView = itemView.findViewById(R.id.thumbnailImageView)
        private val titleTextView: TextView = itemView.findViewById(R.id.videoTitleTextView)
        private val rewardTextView: TextView = itemView.findViewById(R.id.videoRewardTextView)

        fun bind(video: Video) {
            titleTextView.text = video.title
            rewardTextView.text = "Reward: +${video.reward} pts"

            // Load thumbnail using Glide
            Glide.with(itemView.context)
                .load(video.thumbnail)
                .placeholder(android.R.drawable.ic_media_play)
                .into(thumbnailImageView)

            itemView.setOnClickListener {
                onVideoClick(video)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(videos[position])
    }

    override fun getItemCount(): Int = videos.size
}
