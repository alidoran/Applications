package alidoran.safelycamera.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alidoran.safelycamera.databinding.PhotoRecyclerItemBinding
import alidoran.safelycamera.listeners.DecryptBmpListener
import alidoran.safelycamera.model.FileModel
import alidoran.safelycamera.model.PhotoRecyclerModel

class PhotoRecyclerAdapter(private val fileModelList: ArrayList<FileModel>,
                           private val decryptBmpListener: DecryptBmpListener
) :
    RecyclerView.Adapter<PhotoRecyclerAdapter.PhotoRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PhotoRecyclerItemBinding.inflate(inflater, parent, false)
        return PhotoRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoRecyclerViewHolder, position: Int) {
        val fileModel = fileModelList[position]
        val photoRecyclerModel = PhotoRecyclerModel(
            fileModel.name,
            fileModel.dateAdded.toString(),
            decryptBmpListener.load(fileModel.uri))
        holder.bind(photoRecyclerModel)
    }

    override fun getItemCount(): Int {
        return fileModelList.size
    }

    inner class PhotoRecyclerViewHolder(private val binding: PhotoRecyclerItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PhotoRecyclerModel){
            binding.model = item
        }
    }
}