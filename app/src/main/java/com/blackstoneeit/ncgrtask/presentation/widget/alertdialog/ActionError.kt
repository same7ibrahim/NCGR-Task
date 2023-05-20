package com.blackstoneeit.ncgrtask.presentation.widget.alertdialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import com.blackstoneeit.ncgrtask.common.extension.inflater
import com.blackstoneeit.ncgrtask.databinding.ActionErrorBinding

class ActionError(private val builder: Builder) {

    private val dialog: AlertDialog = AlertDialog.Builder(builder.activity).create()
    private var binding: ActionErrorBinding

    init {
        val layoutInflater = builder.activity.inflater
        binding = ActionErrorBinding.inflate(layoutInflater, null, false)
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
        binding.messageTv.text = builder.message
    }

    private fun registerClickObservers() {
        binding.btnClose.setOnClickListener {
            dissmisDialog()
            builder.closeAction?.invoke()
        }
    }

    private fun bindLifecycle() {
        binding.lifecycleOwner = builder.activity
        binding.executePendingBindings()
    }

    class Builder(
        internal val activity: FragmentActivity
    ) {

        internal var message: String = ""
        internal var closeAction: (() -> Unit)? = null
        internal var isCancelable: Boolean = true

        fun setMessage(message: String) = apply {
            this.message = message
        }

        fun setMessage(@StringRes title: Int) = apply {
            setMessage(activity.getString(title))
        }

        fun setCloseAction(listener: () -> Unit) = apply {
            closeAction = listener
        }

        fun setIsCancelable(isCancelable: Boolean) = apply {
            this.isCancelable = isCancelable
        }

        fun build(): ActionError {
            return ActionError(this)
        }
    }
}
