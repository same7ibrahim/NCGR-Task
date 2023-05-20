package com.blackstoneeit.ncgrtask.presentation.main.mostviewed.widget

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.blackstoneeit.ncgrtask.databinding.OneArticleItemBinding

class CoursesListAdapter (private val clickListener: OnItemClickListener) :
    ListAdapter<ArticleUiModel, CoursesListAdapter.ViewHolder>(CategoryDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, clickListener, position)
        }
    }

    class ViewHolder private constructor(val binding: OneArticleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: ArticleUiModel,
            clickListener: OnItemClickListener,
            position: Int
        ) {
            binding.model = item
            binding.clickListener = clickListener
            binding.position = position
            binding.executePendingBindings()


        }


        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = OneArticleItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class OnItemClickListener(
    val itemClickListener: (model: ArticleUiModel, position: Int) -> Unit
) {
    fun onItemClick(model: ArticleUiModel, position: Int) =
        itemClickListener(model, position)
}

class CategoryDiffCallback : DiffUtil.ItemCallback<ArticleUiModel>() {
    override fun areItemsTheSame(oldItem: ArticleUiModel, newItem: ArticleUiModel): Boolean {
        return oldItem === newItem /// === means reference comparing ...
    }

    override fun areContentsTheSame(oldItem: ArticleUiModel, newItem: ArticleUiModel): Boolean {
        return oldItem == newItem
    }

}