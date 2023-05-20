package com.blackstoneeit.ncgrtask.presentation.widget.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListWidget<Binding : ViewDataBinding, Model : Any, Adapter : ListAdapter<Model, *>>(
    context: Context, attrs: AttributeSet
) : BaseConstraintLayout(context, attrs) {

    var itemClickCallback: ((Model, Int) -> Unit)? = null

    protected lateinit var binding: Binding
    protected lateinit var adapter: Adapter
    private lateinit var items: MutableList<Model>

    init {
        initView(context)
    }

    private fun initView(context: Context) {
        val layoutInflater = LayoutInflater.from(context)
        binding = DataBindingUtil.inflate(layoutInflater, layoutResId(), this, true)

        initAdapter()
        initRecycler()

    }

    @LayoutRes
    abstract fun layoutResId(): Int
    abstract fun initAdapter()
    abstract fun recyclerView(): RecyclerView



    private fun initRecycler() {
        addAnimation()
        assignAdapter()
    }


    open fun addAnimation() {
        recyclerView().itemAnimator = null
    }

    open fun assignAdapter() {
        recyclerView().adapter = adapter
    }

    fun submitListToAdapter(list: MutableList<Model>, callback: (() -> Unit)? = null) {
        items = list
        adapter.submitList(items) {
            callback?.invoke()
        }
    }

    fun isListInitialized() = ::items.isInitialized
}