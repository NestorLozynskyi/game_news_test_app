package com.test.gamenews.base.globalFuns

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.test.gamenews.base.BaseFragment
import androidx.fragment.app.commit
import com.test.gamenews.base.BaseViewModel

fun FragmentActivity.initialFragment(fragment: BaseFragment) {
    val containerId = ViewModelProviders.of(this)[BaseViewModel::class.java].parentLayoutId
    supportFragmentManager.commit(allowStateLoss = true) {
        replace(containerId, fragment)
    }
}