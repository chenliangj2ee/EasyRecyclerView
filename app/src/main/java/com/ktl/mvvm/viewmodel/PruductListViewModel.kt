package com.ktl.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ktl.mvvm.API
import com.ktl.mvvm.Callback
import com.ktl.mvvm.model.Product

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
}