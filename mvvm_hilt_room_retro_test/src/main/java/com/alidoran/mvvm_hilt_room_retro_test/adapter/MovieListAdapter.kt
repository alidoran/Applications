package com.alidoran.mvvm_hilt_room_retro_test.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alidoran.mvvm_hilt_room_retro_test.R
import com.alidoran.mvvm_hilt_room_retro_test.databinding.FragmentInsertMovieBinding
import com.alidoran.mvvm_hilt_room_retro_test.databinding.MovieItemBinding
import com.alidoran.mvvm_hilt_room_retro_test.model.Movie

class MovieListAdapter(
    private val movieList: List<Movie>
) : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {


    private lateinit var movieDeleteListener: OnMovieDeleteListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class MovieViewHolder(
        private val binding: MovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.onClickListener =
                View.OnClickListener { movieDeleteListener.onDelete(binding.model) }
        }

        fun bind(movie: Movie) {
            binding.model = movie
        }
    }

    fun setDeleteListener(movieDeleteListener: OnMovieDeleteListener) {
        this.movieDeleteListener = movieDeleteListener
    }

    interface OnMovieDeleteListener {
        fun onDelete(movie: Movie?)
    }
}
