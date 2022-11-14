package com.alidoran.mvvm_hilt_room_retro_test.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.alidoran.mvvm_hilt_room_retro_test.adapter.MovieListAdapter
import com.alidoran.mvvm_hilt_room_retro_test.databinding.FragmentShow250TopMovieBinding
import com.alidoran.mvvm_hilt_room_retro_test.tools.ShowWait
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
        initView()
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadRecycler()
        initEvent()
    }

    private fun initView() {
        binding.swipeMovieList.setOnRefreshListener(this)
        binding.swipeMovieList.setProgressViewEndTarget(false, 0)
        ShowWait(binding.root).addLiveCustomWait(this as LifecycleOwner, vm.showWaitLive)
    }

    private fun loadRecycler() {
        val movieAdapter = MovieListAdapter()
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

    private fun fillAdapterByLiveData(adapter: MovieListAdapter) =
        vm.getLiveData().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

    private fun initEvent(){
        binding.fabAdd.setOnClickListener {
            findNavController(this)
                .navigate(vm.getAddMovieAction())
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