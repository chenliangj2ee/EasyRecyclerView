package com.ktl.mvvm.act

import android.view.View
import android.widget.Toast
import com.chenliang.library.base.MyBaseActivity
import com.ktl.mvvm.R
import com.ktl.mvvm.databinding.*
import com.ktl.mvvm.model.Product
import com.ktl.mvvm.viewmodel.PruductListViewModel
import com.ktl.mvvm.viewmodel.n
import com.ktl.mvvm.viewmodel.obs
import com.ktl.mvvm.viewmodel.y
import kotlinx.android.synthetic.main.activity_recycleview.*

class RecyclerViewActivity : MyBaseActivity<ActivityRecycleviewBinding, PruductListViewModel>() {


    override fun layoutId(): Int {
        return R.layout.activity_recycleview;
    }


    override fun initViewModelClass(): Class<PruductListViewModel> {
        return PruductListViewModel::class.java
    }

    /**
     * model必须继承RecyclerViewData ，itemType对应布局类型，如下0，1，2，对应的布局依次是
     * 如果后台给的type类型不是itemType，请使用@SerializedName自定义名字为itemType，itemType为int类型数据
     */
    override fun initCreate() {

        refresh.putItemByType("0", R.layout.item_product_0)
        refresh.putItemByType("1", R.layout.item_product_1)
        refresh.putItemByType("2", R.layout.item_product_2)
        refresh.bindData<Product> {
            if (it.itemType == 0) (it.binding as ItemProduct0Binding).product = it
            if (it.itemType == 1) (it.binding as ItemProduct1Binding).product = it
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