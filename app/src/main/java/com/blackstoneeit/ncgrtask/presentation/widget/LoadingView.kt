package com.blackstoneeit.ncgrtask.presentation.widget

import android.animation.LayoutTransition
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieAnimationView
import com.blackstoneeit.ncgrtask.R
import com.blackstoneeit.ncgrtask.common.extension.attachLayout


class LoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var lottieAnimationView: LottieAnimationView

    init {
        attachLayout(R.layout.loading_view)
        initView()

        lottieAnimationView = findViewById(R.id.lottieAnimationView)
        lottieAnimationView.setAnimation(LOADER_FILE_NAME)
        lottieAnimationView.scale = LOADING_VIEW_SCALE_FACTOR
    }

    private fun initView() {
        layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        this.isFocusable = true
        this.isClickable = true
        this.layoutTransition = LayoutTransition()
        this.setBackgroundColor(ContextCompat.getColor(context, R.color.loading_view_bg_color))
    }

    override fun setVisibility(visibility: Int) {
        super.setVisibility(visibility)
        if (visibility == View.VISIBLE)
            lottieAnimationView.playAnimation()
        else
            lottieAnimationView.cancelAnimation()
    }

    companion object {
        const val LOADER_FILE_NAME = "loader.json"
        const val LOADING_VIEW_SCALE_FACTOR = 0.4f
    }
}
