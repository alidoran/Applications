package com.alidoran.mvvm_hilt_room_retro_test.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alidoran.mvvm_hilt_room_retro_test.R
import com.alidoran.mvvm_hilt_room_retro_test.databinding.FragmentInsertMovieBinding
import com.alidoran.mvvm_hilt_room_retro_test.tools.Status
import com.alidoran.mvvm_hilt_room_retro_test.view_model.InsertMovieViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
        subscribeToObserve()
        initView()
        initEvent()
    }

    private fun subscribeToObserve() {
        findNavController().currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<String>("PictureUrl")
            ?.observe(viewLifecycleOwner) {
                vm.movie.image = it
            }

        vm.insertMovieStatus.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        findNavController()
                            .previousBackStackEntry
                            ?.savedStateHandle?.set(
                                "isChange",
                                result.data?.title
                            )
                        findNavController().popBackStack()
                    }
                    Status.ERROR -> {
                        Toast.makeText(
                            requireActivity(),
                            result.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    Status.LOADING -> {

                    }
                }
            }
        }
    }

    private fun initView() {
        binding.movieCount =
            args.movieListSize
        binding.MoviePicture.setOnClickListener {
        }
    }

    private fun initEvent() {
        binding.btnSaveMovie.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Are you sure?")
                .setPositiveButton("Yes") { _, _ ->
                    vm.insertMovie()
                }
                .setNegativeButton("cancel"){ _,_ -> }
                .show()
        }
        binding.MoviePicture.setOnClickListener {
            findNavController().navigate(R.id.action_insertMovieFragment_to_imagePickerFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}