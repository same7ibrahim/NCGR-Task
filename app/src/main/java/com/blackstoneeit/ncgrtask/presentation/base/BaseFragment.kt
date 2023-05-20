package com.blackstoneeit.ncgrtask.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.blackstoneeit.ncgrtask.common.extension.addLoadingView
import com.blackstoneeit.ncgrtask.common.extension.getOrWrapConstraintLayout
import com.blackstoneeit.ncgrtask.presentation.widget.LoadingView
import com.blackstoneeit.ncgrtask.presentation.widget.alertdialog.ActionError
import com.blackstoneeit.ncgrtask.presentation.widget.alertdialog.ActionSuccess

abstract class BaseFragment<Binding : ViewDataBinding> : Fragment() {

    protected lateinit var binding: Binding
    protected abstract val viewModel: BaseViewModel?
    private lateinit var containerView: ConstraintLayout


    private val loadingView by lazy {
        provideLoadingView().also { containerView.addLoadingView(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!::containerView.isInitialized) {
            containerView = constructContentView(inflater, container)
        }
        setupLoadingView()
        onViewBound(binding)
        return containerView
    }

    private fun observeOnErrorState() {
        viewModel?.errorViewState?.observe(viewLifecycleOwner) {
            showErrorPopup(it)
        }
    }

    private fun observeOnSuccessState() {
        viewModel?.successMessage?.observe(viewLifecycleOwner) {
            showSuccessPopup(it){}
        }
    }

    protected fun showErrorPopup(message: String) {
        ActionError.Builder(requireActivity()).apply {
            setMessage(message)

            setCloseAction {

            }
            setIsCancelable(false)
            build().showDialog()
        }
    }

    private fun showSuccessPopup(message: String, closeAction: (() -> Unit)?) {
        ActionSuccess.Builder(requireActivity()).apply {
            setMessage(message)

            setCloseAction {
                closeAction?.invoke()
            }
            setIsCancelable(false)
            build().showDialog()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeOnErrorState()
        observeOnSuccessState()
        onViewBound(binding)
    }

    private fun setupLoadingView() {
        viewModel?.loadingState?.observe(viewLifecycleOwner, Observer {
            loadingView.isVisible = it
        })
    }

    protected open fun provideLoadingView(): View {
        return LoadingView(requireContext())
    }

    abstract fun onViewBound(binding: Binding)

    private fun constructContentView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ConstraintLayout {
        return getContentView(inflater, container)
            .getOrWrapConstraintLayout()
    }


    protected open fun getContentView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        return binding.root
    }

    @LayoutRes
    abstract fun getLayoutResId(): Int
}
