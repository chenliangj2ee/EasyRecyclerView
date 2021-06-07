package com.chenliang.library.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.chenliang.library.R
import com.chenliang.library.utils.show
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader

/**
 * chenliang
 * email:chenliangj2ee@163.com
 * 2021-03-13
 */

/*
 *
 * 布局说明：
    SmartRefreshLayout
        FrameLayout
            GroupView
            MyRecyclerView
 */

/*
 * 布局说明：
        <com.chenliang.library.view.MyRefreshRecyclerView
            android:id="@+id/refresh"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:empty_layout="@layout/layout_empty"
            app:item="@layout/item_product" />
布局参数说明：
            app:empty_layout="@layout/layout_empty"：列表数据为空时显示的布局
            app:item="@layout/item_layout"：item所对应的layout布局
 代码：
 * 绑定item
            refresh.binding(fun(binding: ItemBinding, bean: Product) {
                    binding.bean = bean
                })
*数据加载监听：下拉刷新，上拉加载
            refresh.load() {
                    分页请使用refresh.pageSize,refresh.pageIndex数据传递给后台
                }
数据更新：数据加载后，添加到列表：
        recyclerView.addData(list)
 */

class MyRefreshRecyclerView : SmartRefreshLayout {

    private var rootView: FrameLayout? = null
    private var emptyLayout: View? = null
    private var recyclerView: MyRecyclerView? = null
    private var params: ViewGroup.LayoutParams? = null

    private var emptyLayoutId: Int = 0
    private var layoutId: Int = 0

    var pageSize: Int = 3
    var defaultPageIndex: Int = 1
    var pageIndex: Int = defaultPageIndex

    private var loadFun: (() -> Unit?)? = null

    constructor(context: Context?) : super(context!!) {

    }

    constructor(context: Context?, attributeSet: AttributeSet) : super(context!!, attributeSet) {
        val typedArray =
            context.obtainStyledAttributes(attributeSet, R.styleable.MyRefreshRecyclerView)
        emptyLayoutId = typedArray.getResourceId(R.styleable.MyRefreshRecyclerView_empty_layout, 0)
        layoutId = typedArray.getResourceId(R.styleable.MyRefreshRecyclerView_item, 0)
        params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        setRefreshHeader(ClassicsHeader(context));
        setRefreshFooter(ClassicsFooter(context));
        setEnableAutoLoadMore(false)
        initRoot(context, attributeSet)
        initEmpty(context, attributeSet)
        initRecyclerView(context, attributeSet)
        initLoadListener()
    }

    private fun initRoot(context: Context?, attributeSet: AttributeSet) {
        rootView = FrameLayout(context!!)
        addView(rootView, params)
    }

    private fun initEmpty(context: Context?, attributeSet: AttributeSet) {
        if (emptyLayoutId != 0) {
            emptyLayout = View.inflate(context, emptyLayoutId, null)
            rootView!!.addView(emptyLayout, params)
        }
    }

    private fun initRecyclerView(context: Context?, attributeSet: AttributeSet) {
        recyclerView = MyRecyclerView(context!!, attributeSet)
        rootView!!.addView(recyclerView, params)
    }

    private fun initLoadListener() {
        setEnableRefresh(true)
        setEnableLoadMore(true)

        this.setOnRefreshListener {
            pageIndex = defaultPageIndex;
            Log.i("MyDog", "下拉刷新,pageIndex:$pageIndex")
            loadFun?.invoke()
        }

        this.setOnLoadMoreListener {
            pageIndex = recyclerView!!.getData<Any>().size / pageSize + defaultPageIndex
            Log.i("MyDog", "加载更多,pageIndex:$pageIndex")
            loadFun?.invoke()
        }
    }

    public fun <B : ViewDataBinding, D : Any> bindData(
        layoutId: Int,
        func: (bind: B, bean: D) -> Unit
    ) {
        recyclerView!!.binding(layoutId, func)

    }

    public fun <B : ViewDataBinding, D : Any> bindData(
        func: (bind: B, bean: D) -> Unit
    ): MyRefreshRecyclerView {
        recyclerView!!.binding(func)
        return this
    }

    public fun loadData(func: () -> Unit): MyRefreshRecyclerView {
        Log.i("MyDog", "自动刷新,pageIndex:$pageIndex")
        loadFun = func
        func()
        return this
    }

    public fun <T : Any> loadData(
        mutableLiveData: MutableLiveData<ArrayList<T>>,
        func: () -> Unit
    ): MyRefreshRecyclerView {
        Log.i("MyDog", "自动刷新,pageIndex:$pageIndex")
        loadFun = func
        func()
        observeData(mutableLiveData)
        return this
    }

    public fun <D : Any> addData(list: ArrayList<D>) {
        if (list != null) {
            if (pageIndex == defaultPageIndex) {
                recyclerView!!.clearData<Any>()
            }
            recyclerView!!.addData(list)

            emptyLayout!!.show(recyclerView!!.getData<Any>().size == 0)
            recyclerView!!.show(recyclerView!!.getData<Any>().size > 0)

            this.setEnableLoadMore(list.size >= pageSize)
        }
        stop()
    }

    private fun stop() {
        this.finishRefresh()
        this.finishLoadMore()
    }

    fun <T : Any> observeData(mutableLiveData: MutableLiveData<ArrayList<T>>) {
        mutableLiveData.observe(this.context as LifecycleOwner, Observer<ArrayList<T>> {
            this.addData(it)
        })
    }

}