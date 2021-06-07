package com.chenliang.library.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * chenliang
 * email:chenliangj2ee@163.com
 * 2021-03-13
 */
open abstract class MyBaseFragment<T : ViewDataBinding, VM : ViewModel> : Fragment() {
    lateinit var binding: T
    lateinit var viewModel: VM
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId(), container, false);
        viewModel = createViewModel(initViewModelClass())
        var view = binding.root;
        initCreate();
        return view
    }

    private fun createViewModel(c: Class<VM>): VM {
        return ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(c)

    }

    abstract fun layoutId(): Int;
    abstract fun initCreate()
    abstract fun initViewModelClass(): Class<VM>

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }
}