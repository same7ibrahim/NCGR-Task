package com.blackstoneeit.ncgrtask.presentation.widget.alertdialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import com.blackstoneeit.ncgrtask.common.extension.inflater
import com.blackstoneeit.ncgrtask.databinding.ActionAlertBinding

class ActionAlert(private val builder: Builder) {

    private val dialog: AlertDialog = AlertDialog.Builder(builder.activity).create()
    private var binding: ActionAlertBinding

    init {
        val layoutInflater = builder.activity.inflater
        binding = ActionAlertBinding.inflate(layoutInflater, null, false)
        dialog.setView(binding.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(builder.isCancelable)
        bindLifecycle()
        registerClickObservers()
        setValues()
    }

    fun showDialog() {
        dialog.show()
    }

    fun dissmisDialog() {
        dialog.dismiss()
    }

    private fun setValues() {
        binding.apply {
            tvTitle.text = builder.title
            if (builder.positiveActionTitle != null) {
                positiveButton.text = builder.positiveActionTitle
            } else {
                positiveButton.isVisible = false
            }
            if (builder.negativeActionTitle != null) {
                negativeButton.text = builder.negativeActionTitle
            } else {
                negativeButton.isVisible = false
            }
            tvMsg.text = builder.message
        }
    }

    private fun registerClickObservers() {
        binding.positiveButton.setOnClickListener {
            dissmisDialog()
            builder.positiveAction?.invoke()
        }

        binding.closeIv.setOnClickListener {
            dissmisDialog()
            builder.closeAction?.invoke()
        }

        binding.negativeButton.setOnClickListener {
            dissmisDialog()
            builder.negativeAction?.invoke()
        }
    }

    private fun bindLifecycle() {
        binding.lifecycleOwner = builder.activity
        binding.executePendingBindings()
    }

    class Builder(
        internal val activity: FragmentActivity
    ) {

        internal var title: String = ""
        internal var message: String = ""
        internal var positiveActionTitle: String? = null
        internal var negativeActionTitle: String? = null
        internal var positiveAction: (() -> Unit)? = null
        internal var negativeAction: (() -> Unit)? = null
        internal var closeAction: (() -> Unit)? = null
        internal var isCancelable: Boolean = true

        fun setTitle(title: String) = apply {
            this.title = title
        }

        fun setTitle(@StringRes title: Int) = apply {
            setTitle(activity.getString(title))
        }

        fun setMessage(message: String) = apply {
            this.message = message
        }

        fun setMessage(@StringRes title: Int) = apply {
            setMessage(activity.getString(title))
        }

        fun setPositiveAction(title: String, listener: () -> Unit) = apply {
            positiveActionTitle = title
            positiveAction = listener
        }

        fun setNegativeAction(title: String, listener: () -> Unit) = apply {
            negativeActionTitle = title
            negativeAction = listener
        }

        fun setCloseAction(listener: () -> Unit) = apply {
            closeAction = listener
        }

        fun setPositiveAction(@StringRes title: Int, listener: () -> Unit) = apply {
            setPositiveAction(activity.getString(title), listener)
        }

        fun setNegativeAction(@StringRes title: Int, listener: () -> Unit) = apply {
            setNegativeAction(activity.getString(title), listener)
        }

        fun setIsCancelable(isCancelable: Boolean) = apply {
            this.isCancelable = isCancelable
        }

        fun build(): ActionAlert {
            return ActionAlert(this)
        }
    }
}
