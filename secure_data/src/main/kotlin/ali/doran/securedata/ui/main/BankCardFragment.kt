package ali.doran.securedata.ui.main

import ali.doran.securedata.R
import ali.doran.securedata.databinding.FragmentBankCardBinding
import ali.doran.securedata.model.BankCard
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper

class BankCardFragment : Fragment() {
    lateinit var binding:FragmentBankCardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBankCardBinding.inflate(inflater, container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadRecyclerView()
    }

    fun loadRecyclerView(){
        val bankCardList = arrayListOf<BankCard>(
            BankCard(null, "saderat", "8888 8888 8888 8888", "1234", "98", 1234, "12345"),
            BankCard(null , "saderat2", "2222 8888 8888 8888", "1234", "98", 1234, "12345")
        )

        val adapter = BankCardAdapter(bankCardList)
        binding.recycleBankCard.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recycleBankCard.adapter = adapter
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.recycleBankCard)
    }
}