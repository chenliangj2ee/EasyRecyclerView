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


    /**网络数据加载请参考最新代码：https://github.com/chenliangj2ee/MyRetrofitGo
     * 史上最精简的【带有缓存】的【网络数据加载】封装，Kotlin语言实现Retrofit2 结合OkHttp3网络层，
     * ViewModel技术，使用Kotlin协程，加载网络数据，并添加缓存功能，减轻开发工作，且增加用户体验，
     * 堪称史上最简洁的代码，实现你想要的功能；
     */

    fun getProducts(pageIndex: Int, pageSize: Int) {
        go({ API.getProductList2(pageIndex, pageSize) }) { ps.value = it.bean }
    }

}

