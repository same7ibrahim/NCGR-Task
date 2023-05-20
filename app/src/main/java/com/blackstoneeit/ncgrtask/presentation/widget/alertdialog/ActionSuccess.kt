package com.blackstoneeit.ncgrtask.presentation.widget.alertdialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import com.blackstoneeit.ncgrtask.common.extension.inflater
import com.blackstoneeit.ncgrtask.databinding.ActionSuccessBinding

class ActionSuccess(private val builder: Builder) {

    private val dialog: AlertDialog = AlertDialog.Builder(builder.activity).create()
    private var binding: ActionSuccessBinding

    init {
        val layoutInflater = builder.activity.inflater
        binding = ActionSuccessBinding.inflate(layoutInflater, null, false)
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
        binding.titleTv.text = builder.title
        binding.closeIv.text = builder.btnText
    }

    private fun registerClickObservers() {
        binding.closeIv.setOnClickListener {
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

        internal var title: String = ""
        internal var message: String = ""
        internal var btnText: String? = ""
        internal var closeAction: (() -> Unit)? = null
        internal var isCancelable: Boolean = true

        fun setMessage(message: String) = apply {
            this.message = message
        }

        fun setTitle(title: String) = apply {
            this.title = title
        }

        fun setCloseAction(listener: () -> Unit) = apply {
            closeAction = listener
        }

        fun setButtonText(btnTxt: String){
            this.btnText = btnTxt
        }
        fun setIsCancelable(isCancelable: Boolean) = apply {
            this.isCancelable = isCancelable
        }

        fun build(): ActionSuccess {
            return ActionSuccess(this)
        }
    }
}
