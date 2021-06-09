package com.chenliang.library.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.chenliang.library.bean.Bind

/**
 * chenliang
 * email:chenliangj2ee@163.com
 * 2021-03-13
 */
open class MyRvAdapter<B : ViewDataBinding, D : Any>(
        context: Context, layoutId: Int,
        func: (bind: Bind<B, D>) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var con = context;
    var data = ArrayList<D>()
    var layoutId = layoutId;
    var func = func

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(con)
        val binding: B = DataBindingUtil.inflate(inflater, layoutId, parent, false)
        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding: B? = DataBindingUtil.getBinding(holder.itemView)
        if (binding != null) {
            onBindViewHolder(binding, data[position])
            binding.executePendingBindings()
        }

    }

    private fun onBindViewHolder(binding: B, bean: D) {
        var b = Bind<B, D>()
        b.key = binding
        b.value = bean
        func(b)
    }


    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}