package com.ktl.mvvm.viewmodel

import com.ktl.mvvm.API
import com.ktl.mvvm.act.Bean
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class Test {

    fun t(block:  MyInterface.() -> Unit,i:Int): Unit {



    }
}

fun main() {
    var te=Test()
    te.t({ API.getProductList2(10,10)},10)
}


public interface MyInterface {
}
