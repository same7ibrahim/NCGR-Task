package com.blackstoneeit.ncgrtask.presentation.main.mostviewed.widget

import android.content.Context
import android.util.AttributeSet
import com.blackstoneeit.ncgrtask.R
import com.blackstoneeit.ncgrtask.databinding.WidgetArticlesListBinding
import com.blackstoneeit.ncgrtask.presentation.widget.base.BaseListWidget

class ArticlesListWidget(context: Context, attrs: AttributeSet) :
    BaseListWidget<WidgetArticlesListBinding, ArticleUiModel, CoursesListAdapter>(context, attrs) {

    override fun initAdapter() {
        adapter = CoursesListAdapter(
            OnItemClickListener(
                itemClickListener = { model, position ->
                    itemClickCallback?.invoke(model, position)
                    recyclerView().smoothScrollToPosition(position)
                })
        )
    }

    override fun recyclerView() = binding.recyclerview
    override fun layoutResId() = R.layout.widget_articles_list

}