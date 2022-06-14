package alidoran.safelycamera.view

import alidoran.safelycamera.di.DaggerFileModelFactory
import android.Manifest.permission.CAMERA
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import alidoran.safelycamera.listeners.DecryptBmpListener
import alidoran.safelycamera.viewmodel.MainPhotoViewModel
import com.alidoran.safelycamera.databinding.ActivityPhotoMainBinding

class MainPhotoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhotoMainBinding
    private val thisViewModel: MainPhotoViewModel by viewModels()
    private lateinit var uri: Uri
    private val fileModelProvider = DaggerFileModelFactory.create().provider()
    private lateinit var manager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initEvent()
        loadRecycler()
    }

    private val getCameraPicture =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess){
                thisViewModel.encryption(uri)
                contentResolver.delete(uri, null, null)
                loadRecycler()
            }
        }

    private val requestMultiPermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permission ->
            var result=true
            permission.entries.forEach {
                if(!it.value)
                    result = false
            }
            if(result)
                invokeCamera()
        }

    private fun initEvent() {
        binding.floatBtn.setOnClickListener {
            takePicture()
        }
    }

    private fun takePicture() {
        val permissionList = thisViewModel.permissionDisableList(
            arrayListOf(CAMERA, WRITE_EXTERNAL_STORAGE))
        if (permissionList.isEmpty()) invokeCamera()
        else requestMultiPermission.launch(permissionList.toTypedArray())
    }

    private fun invokeCamera() {
        uri = thisViewModel.createFile(false)
        getCameraPicture.launch(uri)
    }

    private fun loadRecycler() {
        val fileList = fileModelProvider.getUriFileList(
            true,
            thisViewModel.getFilePath(true),
            contentResolver
        )
        manager = GridLayoutManager(this, 2)
        binding.recyclerView.apply {
            adapter = PhotoRecyclerAdapter(fileList, object : DecryptBmpListener {
                override fun load(uri: Uri): Bitmap {
                    return thisViewModel.decryptBmp(uri)
                }

            })
            layoutManager = manager
            isNestedScrollingEnabled = true
        }
    }
}