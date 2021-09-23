package com.test.gamenews.base

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.test.gamenews.ui.mainActivity.MainActivity


abstract class BaseFragment(@LayoutRes val layoutId: Int) : Fragment(layoutId) {

    private lateinit var mainActivity: MainActivity
    protected lateinit var viewModel: BaseViewModel

    override fun onAttach(context: Context) {
        mainActivity = (requireActivity() as MainActivity)
        viewModel = mainActivity.viewModel
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initialize()
        setFocus(view)
        observe()
    }

    abstract fun initialize()

    private fun setFocus(view: View) {
        view.apply {
            isFocusableInTouchMode = true
            requestFocus()
            setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    activity?.onBackPressed()
                }
                true
            }
        }
    }

    protected open fun observe() {
    }
}