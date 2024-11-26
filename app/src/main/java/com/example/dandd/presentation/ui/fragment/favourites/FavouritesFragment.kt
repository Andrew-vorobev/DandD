package com.example.dandd.presentation.ui.fragment.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dandd.presentation.ui.adapter.ItemsRecyclerView
import com.example.dandd.presentation.ui.model.ClassView
import com.example.dungeonanddragonsapp.R
import com.example.dungeonanddragonsapp.databinding.FragmentFavouritesBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouritesFragment : Fragment() {

    private var _binding: FragmentFavouritesBinding? = null

    private val favouritesViewModel: FavouritesViewModel by viewModel()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recyclerViewFragmentFavourites
        val adapter = ItemsRecyclerView()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewLifecycleOwner.lifecycleScope.launch {
            favouritesViewModel.items.collect { items ->
                adapter.submitList(items)
            }
        }

        val bundle = Bundle()

        adapter.setOnClickToDetail(object : ItemsRecyclerView.OnClickToDetail {
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