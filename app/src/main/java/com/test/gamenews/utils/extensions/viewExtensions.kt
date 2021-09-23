package com.test.gamenews.utils.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

internal fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun View.gone(): View {
    visibility = View.GONE
    return this
}

fun View.visible(): View {
    visibility = View.VISIBLE
    return this
}