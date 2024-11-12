package com.example.dandd.presentation.ui.fragment.dataList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dandd.presentation.ui.adapter.ItemsRecyclerView
import com.example.dandd.presentation.ui.model.ClassView
import com.example.dungeonanddragonsapp.R
import com.example.dungeonanddragonsapp.databinding.FragmentDataListBinding
import kotlinx.coroutines.launch
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

        val recyclerView = binding.itemsRecyclerView
        val adapter = ItemsRecyclerView()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        binding.dataListReloadButton.setOnClickListener{
            dataListViewModel.loadItems()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            dataListViewModel.items.collect { items ->
                binding.dataListProgressBar.isInvisible = items.isNotEmpty()
                binding.dataListReloadButton.isInvisible = items.isNotEmpty()
                adapter.submitList(items)
            }
        }

        val bundle = Bundle()

        adapter.setOnButtonClickListener(object : ItemsRecyclerView.OnButtonClickListener {
            override fun onClick(classView: ClassView) {
                bundle.putParcelable("index", classView)
                findNavController().navigate(R.id.navigation_data_detail, bundle)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}