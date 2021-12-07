package com.studies.cinedroid.ui.home.details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.studies.cinedroid.R
import com.studies.cinedroid.domain.model.response.Movies

class MovieDetailsActivity : AppCompatActivity() {
    private val movieTitle by lazy {
        findViewById<TextView>(R.id.movie_title_details_activity)
    }

    private val movieRate by lazy {
        findViewById<TextView>(R.id.movie_rate_details_activity)
    }

    private val movieDescription by lazy {
        findViewById<TextView>(R.id.movie_description_details_activity)
    }

    private val backButton by lazy {
        findViewById<ImageButton>(R.id.ic_back_activity_movie_details)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        setupDetails()
        configBackButton()

    }

    private fun configBackButton() {
        backButton.setOnClickListener {
            finish()
        }
    }

    private fun setupDetails() {
        movieTitle.text = getParam()!!.title
        movieDescription.text = getParam()?.overview ?: ""
        movieRate.text = getParam()!!.vote_average.toString()
    }

    private fun getParam() = intent.getParcelableExtra<Movies>(modelParam)

    companion object {
        private const val modelParam = "modelParam"
        fun newInstance(context: Context, model: Movies): Intent {
            return Intent(context, MovieDetailsActivity::class.java).apply {
                putExtra(modelParam, model)
            }
        }
    }
}