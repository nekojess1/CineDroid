package com.studies.cinedroid.ui.home.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.studies.cinedroid.BuildConfig
import com.studies.cinedroid.R
import com.studies.cinedroid.domain.model.response.Movies


class MovieListAdapter(private val listener: RecyclerViewClickInterface) : RecyclerView.Adapter<MovieListAdapter.MyViewHolder>() {

    private var listData: List<Movies>? = null

    fun setListData(listData: List<Movies>) {
        this.listData = listData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_movies_item_view, parent, false)
        return MyViewHolder(view, listener = listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listData?.get(position)!!)
    }

    override fun getItemCount(): Int {
        return if (listData == null) 0 else listData?.size!!
    }

    class MyViewHolder(view: View, val listener: RecyclerViewClickInterface) : RecyclerView.ViewHolder(view){
        private val movieCard = view.findViewById<ImageView>(R.id.movie_card)
        private val movieTitle = view.findViewById<TextView>(R.id.movie_title)
        private val movieRate = view.findViewById<TextView>(R.id.movie_rate)

        fun bind(data: Movies){
            initParameters(data)
            loadImage(data)
            initListeners(data)
        }

        private fun initParameters(data: Movies) {
            movieTitle.text = data.title
            movieRate.text = data.vote_average.toString()
        }

        private fun initListeners(data: Movies) {
            itemView.setOnClickListener {
                listener.onItemClick(data)
            }
        }

        private fun loadImage(data: Movies) {
            Glide.with(movieCard)
                .load(BuildConfig.IMAGE_URL + data.poster_path)
                .into(movieCard)
        }
    }
}