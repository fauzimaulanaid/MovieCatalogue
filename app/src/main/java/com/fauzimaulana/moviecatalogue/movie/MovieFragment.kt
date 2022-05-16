package com.fauzimaulana.moviecatalogue.movie

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.fauzimaulana.moviecatalogue.R
import com.fauzimaulana.moviecatalogue.core.domain.model.NowPlayingMovie
import com.fauzimaulana.moviecatalogue.core.utils.SortUtils
import com.fauzimaulana.moviecatalogue.core.vo.Resource
import com.fauzimaulana.moviecatalogue.databinding.FragmentMovieBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private var _fragmentMovieBinding: FragmentMovieBinding? = null
    private val fragmentMovieBinding get() = _fragmentMovieBinding!!

    private val movieViewModel: MovieViewModel by viewModel()

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentMovieBinding = FragmentMovieBinding.inflate(inflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        if (activity != null) {

            movieAdapter = MovieAdapter()

            movieViewModel.getMovies().observe(viewLifecycleOwner) { movies ->
                if (movies != null) {
                    when (movies) {
                        is Resource.Loading -> fragmentMovieBinding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            fragmentMovieBinding.progressBar.visibility = View.GONE
                            movieAdapter.submitList(movies.data)
                        }
                        is Resource.Error -> {
                            fragmentMovieBinding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Something went wrong, \n" +
                                    "Please check your internet connection", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            with(fragmentMovieBinding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    private val observer = Observer<List<NowPlayingMovie>> { movies ->
        if (movies != null) {
            movieAdapter.submitList(movies)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_sort, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var sort =""
        when (item.itemId) {
            R.id.action_ascending -> sort = SortUtils.ASCENDING
            R.id.action_descending -> sort = SortUtils.DESCENDING
            R.id.action_random -> sort = SortUtils.RANDOM
        }
        movieViewModel.getSortedMovies(sort, "now_playing_movie").observe(viewLifecycleOwner, observer)
        item.isChecked = true
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentMovieBinding = null
    }
}