package com.fauzimaulana.moviecatalogue.favorite.tv

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
import com.fauzimaulana.moviecatalogue.core.domain.model.OnAirTv
import com.fauzimaulana.moviecatalogue.detail.DetailActivity

class FavoriteTvAdapter : ListAdapter<OnAirTv, FavoriteTvAdapter.FavoriteTvViewHolder>(DIFF_CALLBACK) {

    class FavoriteTvViewHolder(private val binding: ItemsListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteTvShow: OnAirTv) {
            with(binding) {
                tvItemTitle.text = favoriteTvShow.title
                tvItemYear.text = favoriteTvShow.year
                tvItemGenre.text = favoriteTvShow.genre
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_TV_SHOW, favoriteTvShow.tvShowId)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(favoriteTvShow.imagePath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imgPoster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTvViewHolder {
        val itemsFavoriteTvShowBinding = ItemsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteTvViewHolder(itemsFavoriteTvShowBinding)
    }

    override fun onBindViewHolder(holder: FavoriteTvViewHolder, position: Int) {
        val favoriteTvShow = getItem(position)
        holder.bind(favoriteTvShow)
    }

    fun getSwipedData(swipedPosition: Int): OnAirTv = getItem(swipedPosition)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<OnAirTv>() {
            override fun areItemsTheSame(oldItem: OnAirTv, newItem: OnAirTv): Boolean {
                return oldItem.tvShowId == newItem.tvShowId
            }

            override fun areContentsTheSame(oldItem: OnAirTv, newItem: OnAirTv): Boolean {
                return oldItem == newItem
            }
        }
    }
}