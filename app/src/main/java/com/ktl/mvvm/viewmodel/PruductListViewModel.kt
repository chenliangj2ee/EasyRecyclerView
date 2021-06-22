package com.ktl.mvvm.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ktl.mvvm.API
import com.ktl.mvvm.Callback
import com.ktl.mvvm.act.Bean
import com.ktl.mvvm.act.Response
import com.ktl.mvvm.model.Product
import kotlinx.coroutines.*
import java.lang.Exception
//https://www.jianshu.com/p/6a33f367a56d
//https://blog.csdn.net/u010368726/article/details/115225787
//https://blog.csdn.net/sange77/article/details/103959389
class PruductListViewModel : ViewModel() {
    var products = MutableLiveData<ArrayList<Product>>()
    fun getProducts(pageIndex: Int, pageSize: Int) {
        API.getProductList(object : Callback<ArrayList<Product>> {
            override fun success(t: ArrayList<Product>) {
                products.value = t
            }

            override fun failed(m: String) {
            }

        })
    }

    fun test() {
        go<String>({ API.getProductList()!! }) {
            it.yes { Log.i("chenliang", it.data.message);Thread.sleep(5000);  Log.i("chenliang", "000000000000") }
            it.no { Log.i("chenliang", it.data.message) }

        }

    }
}


fun <T> ViewModel.go(
    block: suspend CoroutineScope.() -> Bean<T>,
    funResponse: (Response<T>) -> Unit
) {
    viewModelScope.launch(Dispatchers.IO) {
        Log.i("chenliang", "发起请求...")

        var response = Response<T>()
        response.viewModelScope=viewModelScope
        try {
            var bean = block()
            response.code = 0
            bean.message = "成功"
            response.data = bean;
            funResponse(response)
        } catch (e: Exception) {
            var bean = Bean<T>()
            bean.message = "失败"
            response.code = -1
            response.data = bean
            funResponse(response)
            Log.e("chenliang", e.stackTraceToString())
        }
    }
}

fun <T> Response<T>.yes(func: () -> Unit) = this.apply {
    if (code == 0) {
        viewModelScope.launch(Dispatchers.Main) {
            func()
        }
    }
}

fun <T> Response<T>.no(func: () -> Unit) = this.apply {
    if (code != 0) {
        viewModelScope.launch(Dispatchers.Main) {
            func()
        }
    }
}