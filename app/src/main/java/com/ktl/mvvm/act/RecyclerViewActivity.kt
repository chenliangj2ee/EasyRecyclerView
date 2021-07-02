package com.ktl.mvvm.act

import android.content.ClipData
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.chenliang.library.base.MyBaseActivity
import com.ktl.mvvm.R
import com.ktl.mvvm.databinding.ActivityRecycleviewBinding
import com.ktl.mvvm.databinding.ItemProduct2Binding
import com.ktl.mvvm.databinding.ItemProductBinding
import com.ktl.mvvm.databinding.ItemProductNBinding
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

        refresh.putItemByType("0", R.layout.item_product)
        refresh.putItemByType("1", R.layout.item_product_n)
        refresh.putItemByType("2", R.layout.item_product_2)
        refresh.bindData<Product> {
            if (it.itemType == 0) (it.binding as ItemProductBinding).product = it
            if (it.itemType == 1) (it.binding as ItemProductNBinding).product = it
            if (it.itemType == 2) (it.binding as ItemProduct2Binding).product = it
        }

        refresh.loadData { httpGetData() }

    }

    private fun httpGetData() {
        viewModel.getProducts(refresh.pageIndex, refresh.pageSize)
        viewModel.ps.obs(this) {
            it.y { refresh.addData(it.data) }
            it.n {}
        }
    }


    public fun onItemClick(view: View, p: Product) {
        Toast.makeText(this, "我被点击了", Toast.LENGTH_LONG).show()
    }


}