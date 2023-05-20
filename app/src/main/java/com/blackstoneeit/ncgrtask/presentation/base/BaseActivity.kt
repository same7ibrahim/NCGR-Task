package com.blackstoneeit.ncgrtask.presentation.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.blackstoneeit.ncgrtask.R
import com.blackstoneeit.ncgrtask.common.extension.addLoadingView
import com.blackstoneeit.ncgrtask.common.extension.getOrWrapConstraintLayout
import com.blackstoneeit.ncgrtask.presentation.widget.LoadingView
import com.blackstoneeit.ncgrtask.presentation.widget.alertdialog.ActionError
import com.blackstoneeit.ncgrtask.presentation.widget.alertdialog.ActionSuccess
import com.blackstoneeit.ncgrtask.presentation.widget.alertdialog.ActionUnAuthUserDialog


abstract class BaseActivity<Binding : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: Binding
    protected abstract val viewModel: BaseViewModel
    private lateinit var containerView: ConstraintLayout


    private val loadingView by lazy {
        provideLoadingView().also { containerView.addLoadingView(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val contentView = inflateContentView()
        containerView = contentView.getOrWrapConstraintLayout()
        setContentView(containerView)
        onViewBound(binding)
        observeLoadingView()
        observeOnErrorState()
        observeOnSuccessState()
    }


    abstract fun onViewBound(binding: Binding)


    private fun inflateContentView(): View {
        binding = DataBindingUtil.inflate(layoutInflater, getLayoutResId(), null, false)
        return binding.root
    }


    private fun observeLoadingView() {
        viewModel.loadingState.observe(this) {
            loadingView.isVisible = it
        }
    }

    private fun observeOnErrorState() {
        viewModel.errorViewState.observe(this) {
            showErrorPopup(it)
        }
    }

    private fun observeOnSuccessState() {
        viewModel.successMessage.observe(this) {
            showSuccessPopup(it, "")
        }
    }

    private fun showErrorPopup(
        message: String,
        callback: (() -> Unit)? = null,
        isCancelable: Boolean = true
    ) {
        ActionError.Builder(this).apply {
            setMessage(message)

            setCloseAction {
                callback?.invoke()
            }
            setIsCancelable(isCancelable)
            build().showDialog()
        }
    }

    protected fun showUnAuthUserPopup(
        message: String,
        callback: (() -> Unit)? = null,
        isCancelable: Boolean = true
    ) {
        ActionUnAuthUserDialog.Builder(this).apply {
            setMessage(message)
            setCloseAction {
                callback?.invoke()
            }
            setIsCancelable(isCancelable)
            build().showDialog()
        }
    }

    private fun showSuccessPopup(
        message: String,
        title: String,
        btnText: String? = getString(R.string.close),
        callback: (() -> Unit)? = null
    ) {
        ActionSuccess.Builder(this).apply {
            setMessage(message)
            setTitle(title)
            setButtonText(btnText.toString())
            setCloseAction {
                callback?.invoke()
            }
            setIsCancelable(true)
            build().showDialog()
        }
    }

    protected open fun provideLoadingView(): View {
        return LoadingView(this)
    }

    @LayoutRes
    abstract fun getLayoutResId(): Int



}
