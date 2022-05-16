package com.fauzimaulana.moviecatalogue.favorite.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fauzimaulana.moviecatalogue.R
import com.fauzimaulana.moviecatalogue.core.domain.model.NowPlayingMovie
import com.fauzimaulana.moviecatalogue.favorite.databinding.FragmentFavoriteMovieBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteMovieFragment : Fragment() {

    private var _fragmentFavoriteMovieBinding: FragmentFavoriteMovieBinding? = null
    private val fragmentFavoriteMovieBinding get() = _fragmentFavoriteMovieBinding!!

    private val favoriteMovieViewModel: FavoriteMovieViewModel by viewModel()

    private lateinit var favoriteMovieAdapter: FavoriteMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentFavoriteMovieBinding = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
        return fragmentFavoriteMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(fragmentFavoriteMovieBinding.rvFavoriteMovie)

        if (activity != null) {
            favoriteMovieAdapter = FavoriteMovieAdapter()

            favoriteMovieViewModel.getFavoriteMovies().observe(viewLifecycleOwner) { favoriteMovies ->
                if (favoriteMovies != null) {
                    favoriteMovieAdapter.submitList(favoriteMovies)
                }
            }

            with(fragmentFavoriteMovieBinding.rvFavoriteMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favoriteMovieAdapter
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
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val movieEntity = favoriteMovieAdapter.getSwipedData(swipedPosition)
                movieEntity.let { favoriteMovieViewModel.setFavoriteMovie(it) }

                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { _ ->
                    val newMovieEntity = NowPlayingMovie(
                        movieEntity.movieId,
                        movieEntity.title,
                        movieEntity.year,
                        movieEntity.imagePath,
                        movieEntity.genre,
                        !movieEntity.favorite
                    )
                    newMovieEntity.let { favoriteMovieViewModel.setFavoriteMovie(it) }
                }
                snackbar.show()
            }
        }
    })

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentFavoriteMovieBinding = null
    }
}