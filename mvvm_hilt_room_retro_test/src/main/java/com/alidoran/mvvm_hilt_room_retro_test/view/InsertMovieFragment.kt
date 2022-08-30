package com.alidoran.mvvm_hilt_room_retro_test.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alidoran.mvvm_hilt_room_retro_test.R
import com.alidoran.mvvm_hilt_room_retro_test.databinding.FragmentInsertMovieBinding
import com.alidoran.mvvm_hilt_room_retro_test.view_model.InsertMovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InsertMovieFragment : Fragment() {

    private var _binding: FragmentInsertMovieBinding? = null
    private val binding
        get() = _binding!!
    private val vm by viewModels<InsertMovieViewModel>()
    private val args: InsertMovieFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInsertMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.model = vm.movie
        initView()
        initEvent()
    }

    private fun initView() {
        binding.txtMovieCount.text =
            args.movieListSize.toString()
        binding.MoviePicture.setOnClickListener{
        }
    }

    private fun initEvent() {
        findNavController().currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<String>("PictureUrl")
            ?.observe(viewLifecycleOwner){
                vm.movie.image = it
            }

        binding.btnSaveMovie.setOnClickListener {
            findNavController()
                .previousBackStackEntry
                ?.savedStateHandle?.set(
                "isChange",
                vm.insertMovie() > 0
            )
            findNavController().popBackStack()
        }

        binding.MoviePicture.setOnClickListener{
            findNavController().navigate(R.id.action_insertMovieFragment_to_imagePickerFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}