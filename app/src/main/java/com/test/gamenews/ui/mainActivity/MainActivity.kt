package com.test.gamenews.ui.mainActivity

import android.content.*
import androidx.lifecycle.ViewModelProvider
import com.test.gamenews.R
import com.test.gamenews.base.BaseActivity
import com.test.gamenews.base.BaseViewModel
import com.test.gamenews.base.globalFuns.initialFragment
import com.test.gamenews.ui.screens.StartScreen
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity(R.layout.activity_main) {

    val viewModel by viewModel<BaseViewModel>()


    override fun onActivityCreated() {
        viewModel.apply {
            parentLayoutId = R.id.fragmentContainer
        }
        startFragment()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let {
            startFragment()
        }
    }

    private fun startFragment() {
        initialFragment(StartScreen())
    }
}