package com.blackstoneeit.ncgrtask.common.utils

import android.content.Context
import android.content.res.Configuration
import java.util.*

class ResourceProvider(private val context: Context) :
    IResourceProvider {
    override fun getString(resId: Int): String {
        val currentLocale = Locale("en")
        val config = Configuration(context.resources.configuration)
        config.setLocale(currentLocale)
        return context.createConfigurationContext(config).getText(resId).toString()
    }

}
