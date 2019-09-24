package com.sun.qrcodedemo.ui.screen.main

import com.sun.qrcodedemo.R
import com.sun.qrcodedemo.databinding.FragmentMainBinding
import com.sun.qrcodedemo.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {
    override val viewModel: MainViewModel by viewModel()
    override val layoutId: Int = R.layout.fragment_main
}
