package com.alidoran.mvvm_hilt_room_retro_test.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alidoran.mvvm_hilt_room_retro_test.databinding.FragmentImagePickerBinding
import com.alidoran.mvvm_hilt_room_retro_test.view_model.ImagePickerViewModel


class ImagePickerFragment : Fragment() {

    private val vm  by viewModels<ImagePickerViewModel>()
    private var _binding: FragmentImagePickerBinding? = null
    private val binding
        get() = _binding!!
    private lateinit var filePickerLauncher:ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        filePickerLauncher= registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == Activity.RESULT_OK){
                val data = it.data?.dataString
                findNavController().previousBackStackEntry?.savedStateHandle?.set("PictureUrl", data)
                findNavController().popBackStack()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImagePickerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEvent()
    }

    private fun initEvent() {
        binding.btnChoosePhoto.setOnClickListener {
            filePickerLauncher.launch(vm.getChooseFileIntent())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}