package ali.doran.securedata.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ali.doran.securedata.databinding.BankCardItemBinding
import ali.doran.securedata.model.BankCard

class BankCardAdapter (private val bankCardList: ArrayList<BankCard>)
    : RecyclerView.Adapter<BankCardAdapter.BankCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankCardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BankCardItemBinding.inflate(inflater, parent, false)
        return BankCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BankCardViewHolder, position: Int) {
        holder.bind(bankCardList[position])
    }

    override fun getItemCount(): Int {
        return bankCardList.size
    }

    inner class BankCardViewHolder(private val binding: BankCardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(item: BankCard){
                binding.model = item
            }
    }
}