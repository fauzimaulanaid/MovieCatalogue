package com.fauzimaulana.moviecatalogue.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.fauzimaulana.moviecatalogue.R
import com.fauzimaulana.moviecatalogue.core.domain.model.Movie
import com.fauzimaulana.moviecatalogue.core.domain.model.TvShow
import com.fauzimaulana.moviecatalogue.core.vo.Resource
import com.fauzimaulana.moviecatalogue.databinding.ActivityDetailBinding
import com.fauzimaulana.moviecatalogue.databinding.ContentDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private var _detailContentBinding: ContentDetailBinding? = null
    private val detailContentBinding get() = _detailContentBinding!!

    private val detailViewModel: DetailViewModel by viewModel()

    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        _detailContentBinding = activityDetailBinding.detailContent
        setContentView(activityDetailBinding.root)

        setSupportActionBar(activityDetailBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getString(EXTRA_MOVIE)
            if (movieId != null) {
                detailViewModel.selectedItem(movieId)

                detailViewModel.getDetailMovie.observe(this) { movie ->
                    when (movie) {
                        is Resource.Loading -> {
                            detailContentBinding.progressBar.visibility = View.VISIBLE
                            detailContentBinding.content.visibility = View.INVISIBLE
                        }
                        is Resource.Success-> {
                            detailContentBinding.progressBar.visibility = View.GONE
                            detailContentBinding.content.visibility = View.VISIBLE
                            showDetailMovie(movie.data)
                        }
                        is Resource.Error -> {
                            detailContentBinding.progressBar.visibility = View.GONE
                            Toast.makeText(this, "Something went wrong, \nPlease check your internet connection", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            val tvId = extras.getString(EXTRA_TV_SHOW)
            if (tvId != null) {
                detailViewModel.selectedItem(tvId)
                detailViewModel.getDetailTvShow.observe(this) { tvShow ->
                    when (tvShow) {
                        is Resource.Loading -> {
                            detailContentBinding.progressBar.visibility = View.VISIBLE
                            detailContentBinding.content.visibility = View.INVISIBLE
                        }
                        is Resource.Success -> {
                            detailContentBinding.progressBar.visibility = View.GONE
                            detailContentBinding.content.visibility = View.VISIBLE
                            showDetailTvShow(tvShow.data)
                        }
                        is Resource.Error -> {
                            detailContentBinding.progressBar.visibility = View.GONE
                            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun showDetailMovie(movieEntity: Movie?) {
        with(detailContentBinding) {
            "${movieEntity?.title} (${movieEntity?.year})".also { textTitle.text = it }
            textGenre.text = movieEntity?.genre
            Glide.with(this@DetailActivity)
                .load(movieEntity?.imagePath)
                .transform(RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
                .into(imagePoster)
            textRating.text = movieEntity?.rating
            textRuntime.text = movieEntity?.runtime
            textTaglineOrCreator.text = resources.getString(R.string.tagline)
            textTaglineOrCreatorText.text = movieEntity?.tagline
            textSynopsysBody.text = movieEntity?.synopsys
            textBudgetOrSeasons.text = resources.getString(R.string.budget)
            textBudgetOrSeasonsBody.text = movieEntity?.budget
            textRevenueOrEpisodes.text = resources.getString(R.string.revenue)
            textRevenueOrEpisodesBody.text = movieEntity?.revenue
            textReleaseDateBody.text = movieEntity?.releaseDate
            textLanguagesBody.text = movieEntity?.language
            textCountriesBody.text = movieEntity?.countriesOfOrigin
            textCompanyBody.text = movieEntity?.productionCompanies
            textHomepageBody.text = movieEntity?.homepage
        }
    }

    private fun showDetailTvShow(tvEntity: TvShow?) {
        with(detailContentBinding) {
            "${tvEntity?.title} (${tvEntity?.year})".also { textTitle.text = it }
            textGenre.text = tvEntity?.genre
            Glide.with(this@DetailActivity)
                .load(tvEntity?.imagePath)
                .transform(RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
                .into(imagePoster)
            textRating.text = tvEntity?.rating
            textRuntime.text = tvEntity?.runtime
            textTaglineOrCreator.text = resources.getString(R.string.creator)
            textTaglineOrCreatorText.text = tvEntity?.creator
            textSynopsysBody.text = tvEntity?.synopsys
            textBudgetOrSeasons.text = resources.getString(R.string.seasons)
            textBudgetOrSeasonsBody.text = tvEntity?.seasons
            textRevenueOrEpisodes.text = resources.getString(R.string.episodes)
            textRevenueOrEpisodesBody.text = tvEntity?.episodes
            textReleaseDateBody.text = tvEntity?.firstAirDate
            textLanguagesBody.text = tvEntity?.language
            textCountriesBody.text = tvEntity?.countriesOfOrigin
            textCompanyBody.text = tvEntity?.productionCompanies
            textHomepageBody.text = tvEntity?.homepage
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getString(EXTRA_MOVIE)
            if (movieId != null) {
                detailViewModel.getMovie.observe(this) { movie ->
                    val state = movie.favorite
                    setFavoriteState(state)
                }
            } else {
                detailViewModel.getTvShow.observe(this) { tvShow ->
                    val state = tvShow.favorite
                    setFavoriteState(state)
                }
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val extras = intent.extras
        when (item.itemId) {
            R.id.action_favorite -> {
                if (extras != null) {
                    val movieId = extras.getString(EXTRA_MOVIE)
                    if (movieId != null) {
                        val movie = detailViewModel.getMovie.value
                        if (movie?.favorite == true) {
                            showAlertDeleted(movie.title, "Movie")
                        } else {
                            detailViewModel.setFavoriteMovie()
                            val added = resources.getString(R.string.added, movie?.title)
                            Toast.makeText(this, added, Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        val tvShow = detailViewModel.getTvShow.value
                        if (tvShow?.favorite == true) {
                            showAlertDeleted(tvShow.title, "TV Show")
                        } else {
                            detailViewModel.setFavoriteTvShow()
                            val added = resources.getString(R.string.added, tvShow?.title)
                            Toast.makeText(this, added, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                return true
            }
            R.id.action_share -> {
                if (extras != null) {
                    val movieId = extras.getString(EXTRA_MOVIE)
                    if (movieId != null) {
                        detailViewModel.getDetailMovie.observe(this) { movie ->
                            onShareClick(movie.data, null)
                        }
                    } else {
                        detailViewModel.getDetailTvShow.observe(this) { tvShow ->
                            onShareClick(null, tvShow.data)
                        }
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24)
        }
    }

    private fun onShareClick(movie: Movie?, tvShow: TvShow?) {
        val mimeType = "text/plain"
        if (movie != null) {
            ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setText(resources.getString(R.string.share_text, "${movie.title} (${movie.year})", movie.genre, movie.synopsys))
                .startChooser()
        }
        if (tvShow != null) {
            ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setText(resources.getString(R.string.share_text, "${tvShow.title} (${tvShow.year})", tvShow.genre, tvShow.synopsys))
                .startChooser()
        }
    }

    private fun showAlertDeleted(title: String, type: String) {
        val dialogTitle = resources.getString(R.string.alert)
        val dialogMessage = resources.getString(R.string.alert_message, title, type)

        val textYes = resources.getString(R.string.yes)
        val textNo = resources.getString(R.string.no)

        val alertDialogBuilder = AlertDialog.Builder(this)

        alertDialogBuilder
            .setTitle(dialogTitle)
            .setMessage(dialogMessage)
            .setCancelable(false)
            .setPositiveButton(textYes) { _, _ ->
                val extras = intent.extras
                if (extras != null) {
                    val movieId = extras.getString(EXTRA_MOVIE)
                    if (movieId != null) {
                        detailViewModel.setFavoriteMovie()
                    } else {
                        detailViewModel.setFavoriteTvShow()
                    }
                }
                val removed = resources.getString(R.string.removed, title)
                Toast.makeText(this, removed, Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton(textNo) { dialog, _ -> dialog.cancel() }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _detailContentBinding = null
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }
}