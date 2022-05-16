package com.fauzimaulana.moviecatalogue.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fauzimaulana.moviecatalogue.R
import com.fauzimaulana.moviecatalogue.core.databinding.ItemsListBinding
import com.fauzimaulana.moviecatalogue.core.domain.model.NowPlayingMovie
import com.fauzimaulana.moviecatalogue.detail.DetailActivity

class MovieAdapter : ListAdapter<NowPlayingMovie, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    class MovieViewHolder(private val binding: ItemsListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: NowPlayingMovie) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvItemYear.text = movie.year
                tvItemGenre.text = movie.genre
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_MOVIE, movie.movieId)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(movie.imagePath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imgPoster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsMovieBinding = ItemsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NowPlayingMovie>() {
            override fun areItemsTheSame(oldItem: NowPlayingMovie, newItem: NowPlayingMovie): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItem: NowPlayingMovie, newItem: NowPlayingMovie): Boolean {
                return oldItem == newItem
            }
        }
    }
}