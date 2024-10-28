package com.example.dandd.presentation.ui.fragment.dataList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dandd.presentation.ui.adapter.FilmsRecyclerViewAdapter
import com.example.dungeonanddragonsapp.R
import com.example.dungeonanddragonsapp.databinding.FragmentDataListBinding
import com.example.dungeonanddragonsapp.presentation.ui.model.item.ItemView
import org.koin.androidx.viewmodel.ext.android.viewModel

class DataListFragment(
) : Fragment() {

    private var _binding: FragmentDataListBinding? = null

    private val dataListViewModel: DataListViewModel by viewModel()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDataListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.filmsRecyclerView
        val adapter = FilmsRecyclerViewAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter.submitList(dataListViewModel.items.value)

        val bundle = Bundle()

        adapter.setOnButtonClickListener(object : FilmsRecyclerViewAdapter.OnButtonClickListener{
            override fun onClick(itemView: ItemView) {
                bundle.putParcelable("ItemView", itemView)
                findNavController().navigate(R.id.navigation_data_detail, bundle)
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}