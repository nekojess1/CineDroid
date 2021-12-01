package com.studies.cinedroid.ui.home.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.studies.cinedroid.R
import com.studies.cinedroid.domain.model.response.Movies
import com.studies.cinedroid.ui.home.details.MovieDetailsActivity
import com.studies.cinedroid.util.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieListActivity : AppCompatActivity(), RecyclerViewClickInterface {
    lateinit var popularMoviesListAdapter: MovieListAdapter
    lateinit var topMoviesListAdapter: MovieListAdapter
    private val popularMoviesList by lazy {
        findViewById<RecyclerView>(R.id.moviesList)
    }

    private val topMoviesList by lazy {
        findViewById<RecyclerView>(R.id.topMovies)
    }

    private val viewModel: MovieListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        initMovieLists()
        initViewModel()
    }

    private fun initMovieLists() {
        initPopularMoviesList()
        initTopMoviesList()
    }

    private fun initTopMoviesList() {
        topMoviesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        topMoviesListAdapter = MovieListAdapter(this)
        topMoviesList.adapter = topMoviesListAdapter
    }

    private fun initPopularMoviesList() {
        popularMoviesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        popularMoviesListAdapter = MovieListAdapter(this)
        popularMoviesList.adapter = popularMoviesListAdapter
    }

    private fun initViewModel() {
        initObservers()
        executeObservers()
    }

    private fun executeObservers() {
        viewModel.loadPopularMovies()
        viewModel.loadTopMovies()
    }

    private fun initObservers() {
        initPopularMoviesObserver()
        initTopMoviesObserver()
    }

    private fun initTopMoviesObserver() {
        viewModel.getTopMoviesObserver().observe(this, { listMovies ->
            listMovies?.let {
                topMoviesListAdapter.setListData(it)
            } ?: run {
                showToast(getString(R.string.error_api))
            }
        })
    }

    private fun initPopularMoviesObserver() {
        viewModel.getPopularMoviesObserver().observe(this, { listMovies ->
            listMovies?.let {
                popularMoviesListAdapter.setListData(it) }
            ?: run {
                showToast(getString(R.string.error_api))
            }
        })
    }

    override fun onItemClick(data: Movies) {
        startActivity(MovieDetailsActivity.newInstance(this, data))
    }
}