package com.fauzimaulana.moviecatalogue.favorite.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fauzimaulana.moviecatalogue.R
import com.fauzimaulana.moviecatalogue.core.domain.model.OnAirTv
import com.fauzimaulana.moviecatalogue.favorite.databinding.FragmentFavoriteTvBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteTvFragment : Fragment() {

    private var _fragmentFavoriteTvBinding: FragmentFavoriteTvBinding? = null
    private val fragmentFavoriteTvBinding get() = _fragmentFavoriteTvBinding!!

    private val favoriteTvViewModel: FavoriteTvViewModel by viewModel()

    private lateinit var favoriteTvAdapter: FavoriteTvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentFavoriteTvBinding = FragmentFavoriteTvBinding.inflate(inflater, container, false)
        return fragmentFavoriteTvBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(fragmentFavoriteTvBinding.rvFavoriteTv)

        if (activity != null) {
            favoriteTvAdapter = FavoriteTvAdapter()

            favoriteTvViewModel.getFavoriteTvShows().observe(viewLifecycleOwner) { favoriteTvShows ->
                favoriteTvAdapter.submitList(favoriteTvShows)
            }

            with(fragmentFavoriteTvBinding.rvFavoriteTv) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favoriteTvAdapter
            }
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val swipedPosition = viewHolder.adapterPosition
            val tvShowEntity = favoriteTvAdapter.getSwipedData(swipedPosition)
            tvShowEntity.let { favoriteTvViewModel.setFavoriteTvShow(it) }

            val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
            snackbar.setAction(R.string.message_ok) { _ ->
                val newTvShowEntity = OnAirTv(
                    tvShowEntity.tvShowId,
                    tvShowEntity.title,
                    tvShowEntity.year,
                    tvShowEntity.imagePath,
                    tvShowEntity.genre,
                    !tvShowEntity.favorite
                )
                newTvShowEntity.let { favoriteTvViewModel.setFavoriteTvShow(it) }
            }
            snackbar.show()
        }
    })

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentFavoriteTvBinding = null
    }
}