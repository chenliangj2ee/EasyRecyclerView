package com.chenliang.library.bean

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.chenliang.library.R
import com.chenliang.library.utils.show
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader

/**
 * chenliang
 * email:chenliangj2ee@163.com
 * 2021-03-13
 */


class Bind<B : ViewDataBinding,M:Any> {
    lateinit var key: B
    lateinit var value: M
}