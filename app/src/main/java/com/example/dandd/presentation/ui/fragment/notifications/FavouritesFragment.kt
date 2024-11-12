package com.example.dandd.presentation.ui.fragment.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dandd.presentation.ui.adapter.FavouritesRecyclerView
import com.example.dandd.presentation.ui.model.ClassView
import com.example.dungeonanddragonsapp.R
import com.example.dungeonanddragonsapp.databinding.FragmentFavouriteBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouritesFragment : Fragment() {

    private var _binding: FragmentFavouriteBinding? = null

    private val favouritesViewModel: FavouritesViewModel by viewModel()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.favouritesItemsRecyclerView
        val adapter = FavouritesRecyclerView()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewLifecycleOwner.lifecycleScope.launch {
            favouritesViewModel.items.collect { items ->
                binding.favouritesItemsRecyclerView.isInvisible = items.isNotEmpty()
                adapter.submitList(items)
            }
        }

        val bundle = Bundle()

        adapter.setOnClickToDetail(object : FavouritesRecyclerView.OnClickToDetail {
            override fun onClick(classView: ClassView) {
                bundle.putParcelable("index", classView)
                findNavController().navigate(R.id.navigation_data_detail, bundle)
            }
        })

        adapter.setOnClickDelete(object : FavouritesRecyclerView.OnClickDelete{
            override fun onClick(classView: ClassView) {
                favouritesViewModel.delete(classView)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}