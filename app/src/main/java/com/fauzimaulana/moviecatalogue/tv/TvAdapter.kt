package com.fauzimaulana.moviecatalogue.tv

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

class TvAdapter : ListAdapter<OnAirTv, TvAdapter.TvViewHolder>(DIFF_CALLBACK) {

    class TvViewHolder(private val binding: ItemsListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: OnAirTv) {
            with(binding) {
                tvItemTitle.text = tv.title
                tvItemYear.text = tv.year
                tvItemGenre.text = tv.genre
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_TV_SHOW, tv.tvShowId)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(tv.imagePath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imgPoster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val itemsTvBinding = ItemsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvViewHolder(itemsTvBinding)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val tv = getItem(position)
        holder.bind(tv)
    }

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