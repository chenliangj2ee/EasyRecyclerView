package com.chenliang.library.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * chenliang
 * email:chenliangj2ee@163.com
 * 2021-03-13
 */
open class MyLvAdapter<B : ViewDataBinding, D : Any>(
    context: Context, layoutId: Int,
    func: (binding: B, bean: D) -> Unit
) : BaseAdapter() {
    var data = ArrayList<D>()
    var con = context;
    var layoutId = layoutId;
    var func=func
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: B = if (convertView == null) {
            DataBindingUtil.inflate(LayoutInflater.from(con), layoutId, parent, false)
        } else {
            DataBindingUtil.getBinding(convertView)!!
        }
        onBindViewHolder(binding, data[position])
        return binding.root

    }

    private fun onBindViewHolder(binding: B, bean: D) {
        func(binding, bean)
    }

    override fun getItem(position: Int): Any? {
        return data[position];
    }

    override fun getItemId(position: Int): Long {
        return position.toLong();
    }

    override fun getCount(): Int {
        return data.size
    }

}