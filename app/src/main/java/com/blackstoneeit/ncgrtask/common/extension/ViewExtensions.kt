package com.blackstoneeit.ncgrtask.common.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(@LayoutRes resId: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(resId, this, attachToRoot)

fun ViewGroup.attachLayout(@LayoutRes resId: Int) =
    inflate(resId, true)

