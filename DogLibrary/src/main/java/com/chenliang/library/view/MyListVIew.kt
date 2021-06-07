package com.chenliang.library.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.BaseAdapter
import android.widget.ListView
import androidx.databinding.ViewDataBinding
import com.chenliang.library.R
import com.chenliang.library.adapter.MyLvAdapter

/**
 * chenliang
 * email:chenliangj2ee@163.com
 * 2021-03-13
 */

/*
 * 使用说明：
 *
 布局：
        <com.chenliang.library.view.MyListVIew
        android:id="@+id/listview"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:item="@layout/item_layout"/>
布局参数说明：
    app:item="@layout/item_layout"：item所对应的layout布局
 代码：
        listview!!.binding(R.layout.item,
            fun(binding: ItemBinding, bean: MyBean) {
                binding.bean = bean
            })
        或者：xml布局里指定app:item="@layout/item_layout"，使用以下形式
        listview!!.binding(fun(binding: ItemBinding, bean: MyBean) {
                binding.bean = bean
            })
        参数说明：
            R.layout.item：你的item布局
            MyBean：你的数据model
            ItemBinding：你的item布局对应的binding
            binding.bean：bean为你的item布局里声明的变量名称（你可以修改为其他名称）
数据更新：数据加载后，添加到列表：
        listview.addData(list)

 */
class MyListVIew : ListView {
    lateinit var listAdapter: BaseAdapter
    var layoutId: Int = 0

    constructor(context: Context?) : super(context!!) {

    }

    constructor(context: Context?, attributeSet: AttributeSet) : super(context!!, attributeSet) {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.MyListVIew)
        layoutId = typedArray.getResourceId(R.styleable.MyListVIew_item, 0)
    }


    public fun <B : ViewDataBinding, D : Any> binding(
        layoutId: Int,
        func: (bind: B, bean: D) -> Unit
    ) {
        listAdapter = MyLvAdapter<B, D>(context, layoutId!!, func)
        adapter = listAdapter
    }

    public fun <B : ViewDataBinding, D : Any> binding(
        func: (bind: B, bean: D) -> Unit
    ) {
        listAdapter = MyLvAdapter<B, D>(context, layoutId!!, func)
        adapter = listAdapter
    }

    public fun <D : Any> addData(list: ArrayList<D>) {
        (listAdapter as MyLvAdapter<*, D>).data.addAll(list)
        listAdapter.notifyDataSetChanged()
    }

    public fun <D : Any> clearData() {
        (listAdapter as MyLvAdapter<*, D>).data.clear()
        listAdapter.notifyDataSetChanged()
    }

    public fun <D : Any> getData(): ArrayList<D> {
        return (listAdapter as MyLvAdapter<*, D>).data

    }

}