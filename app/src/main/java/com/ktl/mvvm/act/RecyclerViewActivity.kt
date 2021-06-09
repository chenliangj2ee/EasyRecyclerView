package com.ktl.mvvm.act

import android.content.ClipData
import android.view.View
import android.widget.Toast
import com.chenliang.library.base.MyBaseActivity
import com.ktl.mvvm.R
import com.ktl.mvvm.databinding.ActivityRecycleviewBinding
import com.ktl.mvvm.databinding.ItemProductBinding
import com.ktl.mvvm.model.Product
import com.ktl.mvvm.viewmodel.PruductListViewModel
import kotlinx.android.synthetic.main.activity_recycleview.*

class RecyclerViewActivity : MyBaseActivity<ActivityRecycleviewBinding, PruductListViewModel>() {


    override fun layoutId(): Int {
        return R.layout.activity_recycleview;
    }


    override fun initViewModelClass(): Class<PruductListViewModel> {
        return PruductListViewModel::class.java
    }

    override fun initCreate() {


        refresh.bindData<ItemProductBinding, Product> { it.key.product = it.value }
        refresh.loadData(viewModel.products){httpGetData() }


    }

    private fun httpGetData() {
        viewModel.getProducts(refresh.pageIndex, refresh.pageSize)
    }


    public fun onItemClick(view: View, p: Product) {
        Toast.makeText(this, "我被点击了", Toast.LENGTH_LONG).show()
    }


}