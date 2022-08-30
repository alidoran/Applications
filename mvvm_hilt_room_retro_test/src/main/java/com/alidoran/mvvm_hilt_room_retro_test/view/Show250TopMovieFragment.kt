package com.alidoran.mvvm_hilt_room_retro_test.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.alidoran.mvvm_hilt_room_retro_test.adapter.MovieListAdapter
import com.alidoran.mvvm_hilt_room_retro_test.databinding.FragmentShow250TopMovieBinding
import com.alidoran.mvvm_hilt_room_retro_test.view_model.Show250TopMovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Show250TopMovieFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    private var _binding: FragmentShow250TopMovieBinding? = null
    private val binding
        get() = _binding!!
    private val _vm: Show250TopMovieViewModel by viewModels()
    lateinit var vm: Show250TopMovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShow250TopMovieBinding.inflate(inflater, container, false)
        vm= _vm
        return binding.root
    }

    private fun loadRecycler() {
        val movieAdapter = MovieListAdapter()
        if (binding.swipeMovieList.isRefreshing)
            binding.swipeMovieList.isRefreshing = false
        binding.recyclerMovie.apply {
            layoutManager = LinearLayoutManager(activity)
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(
                DividerItemDecoration(
                    activity,
                    DividerItemDecoration.VERTICAL
                )
            )
            isNestedScrollingEnabled = true
            adapter = movieAdapter
        }
        movieAdapter.setOnDeleteListener {
            vm.deleteMovie(it)
        }
        fillAdapterByLiveData(movieAdapter)
    }

    fun fillAdapterByLiveData(adapter: MovieListAdapter) =
        vm.getLiveData().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.swipeMovieList.setOnRefreshListener(this)
        loadRecycler()
        binding.fabAdd.setOnClickListener {
            val movieCount = vm.getMovieCount()
            val action = Show250TopMovieFragmentDirections
                .actionShow250TopMovieFragmentToInsertMovieFragment(movieCount)
            findNavController(this)
                .navigate(action)
        }
    }

    override fun onRefresh() {
        vm.refresh250MoviesFromRepository()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}