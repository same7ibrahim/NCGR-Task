package com.blackstoneeit.ncgrtask.common.extension

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey


val Context.inflater: LayoutInflater
    get() = LayoutInflater.from(this)
