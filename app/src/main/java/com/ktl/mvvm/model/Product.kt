package com.ktl.mvvm.model

import androidx.databinding.BaseObservable

data class Product(
    var name: String,
    var price: String,
    var src: String,
    var from: String,
    var des: String,
    var state: Int
) : BaseObservable()