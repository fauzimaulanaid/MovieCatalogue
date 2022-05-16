package com.fauzimaulana.moviecatalogue.favorite.movie

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

class FavoriteMovieAdapter : ListAdapter<NowPlayingMovie, FavoriteMovieAdapter.FavoriteMovieViewHolder>(DIFF_CALLBACK) {

    class FavoriteMovieViewHolder(private val binding: ItemsListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteMovie: NowPlayingMovie) {
            with(binding) {
                tvItemTitle.text = favoriteMovie.title
                tvItemYear.text = favoriteMovie.year
                tvItemGenre.text = favoriteMovie.genre
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_MOVIE, favoriteMovie.movieId)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(favoriteMovie.imagePath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imgPoster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieViewHolder {
        val itemsFavoriteMovieBinding = ItemsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteMovieViewHolder(itemsFavoriteMovieBinding)
    }

    override fun onBindViewHolder(holder: FavoriteMovieViewHolder, position: Int) {
        val favoriteMovie = getItem(position)
        holder.bind(favoriteMovie)
    }

    fun getSwipedData(swipedPosition: Int): NowPlayingMovie = getItem(swipedPosition)

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