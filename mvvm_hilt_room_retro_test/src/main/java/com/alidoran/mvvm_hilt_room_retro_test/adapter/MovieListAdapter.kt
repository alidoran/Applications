package com.alidoran.mvvm_hilt_room_retro_test.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alidoran.mvvm_hilt_room_retro_test.databinding.MovieItemBinding
import com.alidoran.mvvm_hilt_room_retro_test.model.Movie

class MovieListAdapter :
    ListAdapter<Movie, MovieListAdapter.MovieViewHolder>(
        ListDiffCallback()
    ) {


    private lateinit var movieDeleteListener: ((Movie) -> Unit)
    fun setOnDeleteListener(listener: (Movie) -> Unit) {
        movieDeleteListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(getItem(position))


    inner class MovieViewHolder(
        private val binding: MovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.model = movie
            binding.btnDelete.setOnClickListener {
                movieDeleteListener(movie)
            }
        }
    }

    class ListDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem

    }
}


