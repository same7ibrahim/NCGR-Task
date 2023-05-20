package com.blackstoneeit.ncgrtask.presentation.main.mostviewed

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.blackstoneeit.ncgrtask.R
import com.blackstoneeit.ncgrtask.databinding.FragmentMostViewedBinding
import com.blackstoneeit.ncgrtask.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MostViewedFragment : BaseFragment<FragmentMostViewedBinding>() {
    override fun onViewBound(binding: FragmentMostViewedBinding) {

        setupObserver()
        setupArticleList()
    }

    private fun setupObserver() {
        viewModel.articlesList.observe(viewLifecycleOwner) {
            binding.mostViewedList.submitListToAdapter(it.toMutableList())
        }
    }

    private fun setupArticleList() {
        binding.mostViewedList.itemClickCallback = { articleModel, _ ->
            findNavController().navigate(
                MostViewedFragmentDirections.actionHomeFragmentToDetailsFragment(
                    articleModel.articleUrl
                )
            )
        }
    }

    override val viewModel: MostViewedViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_most_viewed
}