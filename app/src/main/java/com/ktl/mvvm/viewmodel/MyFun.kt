package com.ktl.mvvm.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ktl.mvvm.act.Bean
import com.ktl.mvvm.act.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

fun <T> ViewModel.initData(): MutableLiveData<Bean<T>> {
    return MutableLiveData<Bean<T>>()
}

fun <T> ViewModel.initDatas(): MutableLiveData<Bean<ArrayList<T>>> {
    return MutableLiveData<Bean<ArrayList<T>>>()
}
fun <T> ViewModel.go(
    block: suspend CoroutineScope.() -> Bean<T>,
    funResponse: (Response<T>) -> Unit
) {
    viewModelScope.launch(Dispatchers.IO) {
        Log.i("chenliang", "发起请求...")

        var response = Response<T>()
        response.viewModelScope = viewModelScope
        try {
            var bean = block()
            response.code = 0
            bean.message = "成功"
            response.bean = bean;
            funResponse(response)
        } catch (e: Exception) {
            var bean = Bean<T>()
            bean.message = "失败"
            response.code = -1
            response.bean = bean
            funResponse(response)
            Log.e("chenliang", e.stackTraceToString())
        }
    }
}


fun <T> Response<T>.n(func: () -> Unit) = this.apply {
    if (code != 0) {
        viewModelScope.launch(Dispatchers.Main) {
            func()
        }
    }
}


fun Any.y(func: () -> Unit) = this.apply {
    if ((this as Bean<Any>).code == 0) {
        func()
    }
}

fun Any.n(func: () -> Unit) = this.apply {
    if ((this as Bean<Any>).code != 0) {
        func()
    }
}

fun <T> MutableLiveData<T>.obs(owner: LifecycleOwner, func: (t: T) -> Unit) = this.apply {
    this.observe(owner, Observer<T> {
        func(it)
    })
}