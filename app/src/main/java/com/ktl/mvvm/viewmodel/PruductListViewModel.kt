package com.ktl.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.ktl.mvvm.API
import com.ktl.mvvm.model.Product

//https://www.jianshu.com/p/6a33f367a56d
//https://blog.csdn.net/u010368726/article/details/115225787
//https://blog.csdn.net/sange77/article/details/103959389

class PruductListViewModel : ViewModel() {

    var p = initData<Product>()
    var ps = initDatas<Product>()

    fun getProducts(pageIndex: Int, pageSize: Int) {
        go({ API.getProductList2(pageIndex, pageSize) }) { ps.value = it.bean }
    }

}

