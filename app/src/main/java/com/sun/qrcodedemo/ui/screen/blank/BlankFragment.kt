package com.sun.qrcodedemo.ui.screen.blank

import androidx.fragment.app.Fragment
import com.sun.qrcodedemo.R
import com.sun.qrcodedemo.databinding.FragmentBlankBinding
import com.sun.qrcodedemo.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 *
 */
class BlankFragment : BaseFragment<FragmentBlankBinding, BlankViewModel>() {

    override val viewModel: BlankViewModel by viewModel()
    override val layoutId: Int = R.layout.fragment_blank
}
