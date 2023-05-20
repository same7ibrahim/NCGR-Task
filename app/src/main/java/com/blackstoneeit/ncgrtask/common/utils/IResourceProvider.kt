package com.blackstoneeit.ncgrtask.common.utils

import androidx.annotation.StringRes

interface IResourceProvider {
    fun getString(@StringRes resId: Int): String
}
