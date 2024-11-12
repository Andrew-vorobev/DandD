package com.example.dandd.presentation.ui.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.dandd.presentation.ui.model.ClassView
import com.example.dandd.presentation.ui.model.item.ItemView
import com.example.dandd.presentation.ui.model.skill.toStringBySeparator
import com.example.dungeonanddragonsapp.databinding.FragmentDataDetailBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DataDetailFragment : Fragment() {
    private var _binding: FragmentDataDetailBinding? = null

    private val dataDetailViewModel: DataDetailViewModel by viewModel()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedInstanceState?.let {
            val position = savedInstanceState.getIntArray(ARTICLE_SCROLL_POSITION)
            if (position != null) binding.dataDetailScrollView.post {
                binding.dataDetailScrollView.scrollTo(
                    position[0],
                    position[1]
                )
            }
        }

        val classView: ClassView? = arguments?.getParcelable("index")
        if (classView != null) {
            dataDetailViewModel.load(classView.index)
        }

        lifecycleScope.launch {
            dataDetailViewModel.item.collect { itemView ->
                updateUI(itemView)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putIntArray(
            ARTICLE_SCROLL_POSITION,
            intArrayOf(binding.dataDetailScrollView.scrollX, binding.dataDetailScrollView.scrollY)
        )
    }

    private fun updateUI(itemView: ItemView?) {
        binding.dataDetailName.text = "${itemView?.name}\n"
        binding.dataDetailDesc.text = "${itemView?.desc}"
        binding.dataDetailSkills.text =
            "${itemView?.skills?.toStringBySeparator(selector = { it.desc }, separator = "\n\n")}\n"
    }

    companion object {
        private const val ARTICLE_SCROLL_POSITION = "ARTICLE_SCROLL_POSITION"
    }
}