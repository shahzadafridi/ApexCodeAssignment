package com.apex.codeassesment.util.ex

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle


inline fun <reified T : Activity> Context.navigate(bundle: Bundle? = null) {
    val intent = Intent(this, T::class.java)
    bundle?.let { intent.putExtras(it) }
    startActivity(intent)
}