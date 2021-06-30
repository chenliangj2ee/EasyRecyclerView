package com.ktl.mvvm.model

import com.chenliang.library.adapter.RecyclerViewData

 class Product(
     s: String,
     s1: String,
     s2: String,
     s3: String,
     s4: String,
     i: Int
 ) :RecyclerViewData(){
    var name: String=s
    var price: String=s1
    var src: String=s2
    var from: String=s3
    var des: String=s4
    var state: Int=i
 }