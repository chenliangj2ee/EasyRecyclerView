package com.chenliang.library.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * chenliang
 * email:chenliangj2ee@163.com
 * 2021-03-13
 */
open class MyRvAdapter<D : RecyclerViewData>(
    context: Context, layoutIds: HashMap<String, Int>,
    func: (d: D) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var con = context;
    var data = ArrayList<D>()
    var layoutIds = layoutIds;
    var viewHolders = HashMap<Int, MyViewHolder>()
    var func = func

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var type=viewType
        if (layoutIds.size == 1) {
            type = 31415926
        }

        val inflater: LayoutInflater = LayoutInflater.from(con)
        val layoutId = layoutIds[type.toString()]!!
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, layoutId, parent, false)
        viewHolders[type] = MyViewHolder(binding.root)
        return viewHolders[type]!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.i("MyLog", "onBindViewHolder....")
        val binding: ViewDataBinding = DataBindingUtil.getBinding(holder.itemView)!!
        if (binding != null) {
            data[position].binding = binding
            onBindViewHolder(data[position])
            binding.executePendingBindings()
        }
    }

    private fun onBindViewHolder(bean: D) {
        func(bean)
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].itemType
    }


    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}

open class RecyclerViewData() {
    open var itemType = 0
    open var binding: ViewDataBinding? = null
}