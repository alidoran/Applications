package com.alidoran.mvvm_hilt_room_retro_test.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.alidoran.mvvm_hilt_room_retro_test.adapter.MovieListAdapter
import com.alidoran.mvvm_hilt_room_retro_test.databinding.FragmentShow250TopMovieBinding
import com.alidoran.mvvm_hilt_room_retro_test.model.Movie
import com.alidoran.mvvm_hilt_room_retro_test.view_model.Show250TopMovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Show250TopMovieFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    lateinit var binding: FragmentShow250TopMovieBinding
    private val viewModel: Show250TopMovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShow250TopMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.swipeMovieList.setOnRefreshListener(this)

        viewModel.liveData.observe(viewLifecycleOwner){
            loadRecycler(it)
        }
    }

    private fun loadRecycler(movieList: List<Movie>) {
        if (binding.swipeMovieList.isRefreshing)
            binding.swipeMovieList.isRefreshing = false
        val movieAdapter = MovieListAdapter(movieList)
        movieAdapter.setDeleteListener(object :MovieListAdapter.OnMovieDeleteListener{
            override fun onDelete(movie: Movie) {
                viewModel.deleteMovie(movie)
            }
        })
        binding.recyclerMovie.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(activity)
            isNestedScrollingEnabled = true
        }
    }


    override fun onRefresh() {
        viewModel.refresh250MoviesFromRepository()
    }



}