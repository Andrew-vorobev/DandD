package com.example.dungeonanddragonsapp.presentation.ui.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dungeonanddragonsapp.databinding.FragmentDataDetailBinding
import com.example.dungeonanddragonsapp.presentation.ui.model.item.ItemView
import com.example.dungeonanddragonsapp.presentation.ui.model.skill.toStringBySeparator

class DataDetailFragment : Fragment() {
    private var _binding: FragmentDataDetailBinding? = null

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
            if (position != null) binding.dataDetailScrollView.post(Runnable {
                binding.dataDetailScrollView.scrollTo(
                    position[0],
                    position[1]
                )
            })
        }

        val itemView: ItemView? = arguments?.getParcelable("ItemView")

        with(itemView) {
            binding.dataDetailName.text = "${this?.fullName}\n"
            binding.dataDetailDesc.text =
                "${this?.desc?.toStringBySeparator(selector = { it }, separator = "\n\n")} \n"
            binding.dataDetailSkills.text =
                "${this?.skills?.toStringBySeparator(selector = { it.name }, separator = "\n\n")}\n"
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putIntArray(
            ARTICLE_SCROLL_POSITION,
            intArrayOf(binding.dataDetailScrollView.scrollX, binding.dataDetailScrollView.scrollY)
        )
    }
    companion object{
        private const val ARTICLE_SCROLL_POSITION = "ARTICLE_SCROLL_POSITION"
    }
}