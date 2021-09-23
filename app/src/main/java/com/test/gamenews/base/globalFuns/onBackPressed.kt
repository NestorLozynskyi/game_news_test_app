package com.test.gamenews.base.globalFuns

import android.os.Handler
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.test.gamenews.R

fun FragmentActivity.finishFragment() {
    supportFragmentManager.popBackStack()
}

var exit = false
fun FragmentActivity.exitVariant() {
    if (exit) {
        finishAffinity()
    } else {
        Toast.makeText(this, this.getString(R.string.back_press_again), Toast.LENGTH_SHORT).show()
        exit = true
        Handler().postDelayed({ exit = false }, 2000)
    }
}