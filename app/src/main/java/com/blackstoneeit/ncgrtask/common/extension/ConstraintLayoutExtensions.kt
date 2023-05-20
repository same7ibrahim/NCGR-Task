package com.blackstoneeit.ncgrtask.common.extension

import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.blackstoneeit.ncgrtask.presentation.widget.LoadingView


fun ConstraintLayout.addLoadingView(loadingView: View = LoadingView(context)): View {
    this.addView(loadingView, getParentAlignmentParams(MATCH_PARENT))
    return loadingView
}

fun View.getOrWrapConstraintLayout(): ConstraintLayout {
    return if (this is ConstraintLayout) {
        this
    } else {
        ConstraintLayout(context).apply {
            this.addView(this@getOrWrapConstraintLayout, getParentAlignmentParams(MATCH_PARENT))
        }
    }
}

fun getParentAlignmentParams(parentSizeCriteria: Int): ConstraintLayout.LayoutParams {
    return ConstraintLayout.LayoutParams(parentSizeCriteria, parentSizeCriteria)
        .apply {
            bottomToBottom = ConstraintSet.PARENT_ID
            endToEnd = ConstraintSet.PARENT_ID
            startToStart = ConstraintSet.PARENT_ID
            topToTop = ConstraintSet.PARENT_ID
        }
}

