package com.apex.codeassesment.util.ex

import android.app.Activity
import android.os.Build
import android.os.Parcelable

inline fun <reified T: Parcelable> Activity.parcelable(key: String, isFromBundle: Boolean = false): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (isFromBundle) intent.extras?.getParcelable(key, T::class.java) else intent.getParcelableExtra(key, T::class.java)
    } else {
        if(isFromBundle) intent.extras?.getParcelable(key) else intent.getParcelableExtra(key)
    }
}