package com.fauzimaulana.moviecatalogue.tv

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.fauzimaulana.moviecatalogue.R
import com.fauzimaulana.moviecatalogue.core.domain.model.OnAirTv
import com.fauzimaulana.moviecatalogue.core.utils.SortUtils
import com.fauzimaulana.moviecatalogue.core.vo.Resource
import com.fauzimaulana.moviecatalogue.databinding.FragmentTvBinding
import org.koin.android.viewmodel.ext.android.viewModel


class TvFragment : Fragment() {

    private var _fragmentTvShowBinding: FragmentTvBinding? = null
    private val fragmentTvShowBinding get() = _fragmentTvShowBinding!!

    private val tvViewModel: TvViewModel by viewModel()

    private lateinit var tvShowAdapter: TvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentTvShowBinding = FragmentTvBinding.inflate(inflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        if (activity != null) {
            tvShowAdapter = TvAdapter()

            tvViewModel.getTvShow().observe(viewLifecycleOwner) { tvShows ->
                if (tvShows != null) {
                    when (tvShows) {
                        is Resource.Loading -> fragmentTvShowBinding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            fragmentTvShowBinding.progressBar.visibility = View.GONE
                            tvShowAdapter.submitList(tvShows.data)
                        }
                        is Resource.Error -> {
                            fragmentTvShowBinding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Something went wrong, \n" +
                                    "Please check your internet connection", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            with(fragmentTvShowBinding.rvTv) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }

    private val observer = Observer<List<OnAirTv>> { tvShows ->
        if (tvShows != null) {
            tvShowAdapter.submitList(tvShows)
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
        tvViewModel.getSortedTvShow(sort, "on_air_tv").observe(viewLifecycleOwner, observer)
        item.isChecked = true
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentTvShowBinding = null
    }
}