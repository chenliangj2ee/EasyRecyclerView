package com.ktl.mvvm

interface Callback<T> {
    fun success(t:T)
    fun failed(message:String)
}