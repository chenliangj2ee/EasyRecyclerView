package com.ktl.mvvm.act

import android.content.ClipData
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.chenliang.library.base.MyBaseActivity
import com.ktl.mvvm.R
import com.ktl.mvvm.databinding.ActivityRecycleviewBinding
import com.ktl.mvvm.databinding.ItemProductBinding
import com.ktl.mvvm.model.Product
import com.ktl.mvvm.viewmodel.*
import kotlinx.android.synthetic.main.activity_recycleview.*

class RecyclerViewActivity : MyBaseActivity<ActivityRecycleviewBinding, PruductListViewModel>() {


    override fun layoutId(): Int {
        return R.layout.activity_recycleview;
    }


    override fun initViewModelClass(): Class<PruductListViewModel> {
        return PruductListViewModel::class.java
    }

    override fun initCreate() {

        refresh.recyclerView
        refresh.bindData<ItemProductBinding, Product> {
            it.key.product = it.value;it.key.act = this
        }
//        refresh.loadData(viewModel.products) { httpGetData() }
        refresh.loadData { httpGetData() }

    }

    private fun httpGetData() {
        viewModel.getProducts(refresh.pageIndex, refresh.pageSize)
        viewModel.p2.look(this) {

            if(it.code==0){

            }else{

            }

            when (it.code){
                0 -> Log.i("chenliang", "0")
                -1 -> Log.i("chenliang", "-1")
            }

            y { it.data }
            n { it.data }

        }
    }


    public fun onItemClick(view: View, p: Product) {
        Toast.makeText(this, "我被点击了", Toast.LENGTH_LONG).show()
    }


}