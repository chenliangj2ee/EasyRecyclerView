package com.chenliang.library.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * chenliang
 * email:chenliangj2ee@163.com
 * 2021-03-13
 */
open abstract class MyBaseActivity<Binding : ViewDataBinding, VModel : ViewModel> :
    AppCompatActivity() {

    lateinit var binding: Binding
    lateinit var viewModel: VModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<Binding>(this, layoutId())
        viewModel = createViewModel(initViewModelClass())
        initCreate()
    }

    abstract fun layoutId(): Int
    abstract fun initCreate()
    abstract fun initViewModelClass(): Class<VModel>


    private fun createViewModel(c: Class<VModel>): VModel {
        return ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(c)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }

}