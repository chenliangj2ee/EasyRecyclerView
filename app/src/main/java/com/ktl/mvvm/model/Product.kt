package com.ktl.mvvm.model

import com.chenliang.library.adapter.RecyclerViewData

class Product : RecyclerViewData() {
    var name: String = ""
    var price: String = ""
    var src: String = ""
    var from: String = ""
    var des: String = ""
    var state: Int = 0
}