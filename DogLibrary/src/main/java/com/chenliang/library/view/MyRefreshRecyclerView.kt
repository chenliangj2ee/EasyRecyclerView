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
import com.chenliang.library.adapter.RecyclerViewData
import com.chenliang.library.bean.Bind
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
    open var recyclerView: MyRecyclerView? = null
    private var params: ViewGroup.LayoutParams? = null

    private var emptyLayoutId: Int = 0
    private var layoutIds = ArrayList<Int>()
    var layoutId=-1
    var layoutMap = HashMap<Int, Int>()
    var pageSize: Int = 10
    var defaultPageIndex: Int = 1
    var pageIndex: Int = defaultPageIndex

    private var loadFun: (() -> Unit?)? = null

    constructor(context: Context?) : super(context!!) {}

    constructor(context: Context?, attributeSet: AttributeSet) : super(context!!, attributeSet) {
        val typedArray =
            context.obtainStyledAttributes(attributeSet, R.styleable.MyRefreshRecyclerView)
        emptyLayoutId = typedArray.getResourceId(R.styleable.MyRefreshRecyclerView_empty_layout, 0)
          layoutId = typedArray.getResourceId(R.styleable.MyRefreshRecyclerView_item_layout, 0)

        params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        setRefreshHeader(ClassicsHeader(context));
        setRefreshFooter(ClassicsFooter(context));
        setEnableAutoLoadMore(false)
        initRoot(context, attributeSet)
        initEmpty(context, attributeSet)
        initRecyclerView(context, attributeSet)
        initLoadListener()
        setEnableAutoLoadMore(true)
        setReboundDuration(0)
    }


    fun bindTypeToItemView(type:Int,layoutId:Int){
        layoutMap[type]=layoutId
    }

    private fun initRoot(context: Context?, attributeSet: AttributeSet) {
        rootView = FrameLayout(context!!)
        addView(rootView, params)
    }

    private fun initEmpty(context: Context?, attributeSet: AttributeSet) {
        if (emptyLayoutId != 0) {
            emptyLayout = View.inflate(context, emptyLayoutId, null)
            rootView!!.addView(emptyLayout, params)
            emptyLayout!!.show(false)
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
            pageIndex =
                recyclerView!!.getData<RecyclerViewData>().size / pageSize + defaultPageIndex
            Log.i("MyDog", "加载更多,pageIndex:$pageIndex")
            loadFun?.invoke()
        }
    }


    fun <D : RecyclerViewData> bindData(func: (bind: D) -> Unit): MyRefreshRecyclerView {
        recyclerView!!.layoutIds=layoutMap
        recyclerView!!.layoutId=layoutId
        recyclerView!!.binding(func)
        return this
    }

    fun <T : RecyclerViewData> loadData(
        mutableLiveData: MutableLiveData<ArrayList<T>>,
        func: () -> Unit
    ): MyRefreshRecyclerView {
        loadFun = func
        autoRefresh()
        observeData(mutableLiveData)
        return this
    }

    fun loadData(
        func: () -> Unit
    ): MyRefreshRecyclerView {
        loadFun = func
        autoRefresh()
        return this
    }

    public fun <D : RecyclerViewData> addData(list: ArrayList<D>?) {
        if (list != null) {
            if (pageIndex == defaultPageIndex) {
                recyclerView!!.clearData<RecyclerViewData>()
            }
            recyclerView!!.addData(list)

            emptyLayout!!.show(recyclerView!!.getData<RecyclerViewData>().size == 0)
            recyclerView!!.show(recyclerView!!.getData<RecyclerViewData>().size > 0)

            this.setEnableLoadMore(list.size >= pageSize)
        }
        stop()
    }

    private fun stop() {
        this.finishRefresh()
        this.finishLoadMore()
    }

    private fun <T : RecyclerViewData> observeData(mutableLiveData: MutableLiveData<ArrayList<T>>) {
        mutableLiveData.observe(this.context as LifecycleOwner, Observer<ArrayList<T>> {
            this.addData(it)
        })
    }

}