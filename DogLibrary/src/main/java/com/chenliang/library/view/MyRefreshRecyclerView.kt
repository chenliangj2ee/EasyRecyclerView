package com.chenliang.library.view

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.chenliang.library.R
import com.chenliang.library.adapter.MyRecyclerViewModel
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
    private var topLayout: View? = null
    open var recyclerView: MyRecyclerView? = null
    private var params: ViewGroup.LayoutParams? = null

    private var emptyLayoutId: Int = 0
    private var topLayoutId: Int = 0
    private var layoutIds = ArrayList<Int>()
    var layoutId = -1
    var layoutMap = HashMap<Int, Int>()
    var pageSize: Int = 20
    var defaultPageIndex: Int = 1
    var pageIndex: Int = defaultPageIndex
    var enableTop=false
    private var loadFun: (() -> Unit?)? = null

    constructor(context: Context?) : super(context!!) {}

    constructor(context: Context?, attributeSet: AttributeSet) : super(context!!, attributeSet) {
        val typedArray =
            context.obtainStyledAttributes(attributeSet, R.styleable.MyRefreshRecyclerView)
        emptyLayoutId = typedArray.getResourceId(R.styleable.MyRefreshRecyclerView_my_empty_layout, 0)
        layoutId = typedArray.getResourceId(R.styleable.MyRefreshRecyclerView_my_item_layout, 0)
        topLayoutId = typedArray.getResourceId(R.styleable.MyRefreshRecyclerView_my_top_layout, 0)
        enableTop = typedArray.getBoolean(R.styleable.MyRefreshRecyclerView_my_top_enable, false)

        params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        setRefreshHeader(ClassicsHeader(context));
        setRefreshFooter(ClassicsFooter(context));
        setEnableAutoLoadMore(false)
        initRoot(context, attributeSet)
        initEmpty(context, attributeSet)
        initRecyclerView(context, attributeSet)
        initTop(context, attributeSet)

        initLoadListener()
        setEnableAutoLoadMore(true)
        setReboundDuration(0)
    }


    fun bindTypeToItemView(type: Int, layoutId: Int) {
        layoutMap[type] = layoutId
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            recyclerView!!.setOnScrollChangeListener(OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (topLayout != null) {
                    if (recyclerView!!.getPosition() > 50&&enableTop) {
                        topLayout!!.show(true)
                    } else {
                        topLayout!!.show(false)
                    }

                }
            })
        }
    }

    private fun initTop(context: Context?, attributeSet: AttributeSet) {
        if(topLayoutId==0)
            topLayoutId=R.layout.dog_layout_top
        if (topLayoutId != 0) {
            var params =
                FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            params.gravity =  Gravity.RIGHT +Gravity.BOTTOM
            params.rightMargin=100
            params.bottomMargin=100
            topLayout = View.inflate(context, topLayoutId, null)
            rootView!!.addView(topLayout, params)
            topLayout!!.show(false)
            topLayout!!.setOnClickListener {
                recyclerView!!.smoothScrollToPosition(0)
//                recyclerView!!.scrollToPosition(0)
                topLayout!!.show(false)
            }
        }
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
                recyclerView!!.getData<MyRecyclerViewModel>().size / pageSize + defaultPageIndex
            Log.i("MyDog", "加载更多,pageIndex:$pageIndex")
            loadFun?.invoke()
        }

//        recyclerView.item
    }


    fun <D : MyRecyclerViewModel> bindData(func: (bind: D) -> Unit): MyRefreshRecyclerView {
        recyclerView!!.layoutIds = layoutMap
        recyclerView!!.layoutId = layoutId
        recyclerView!!.binding(func)
        return this
    }

    fun <T : MyRecyclerViewModel> loadData(
        mutableLiveData: MutableLiveData<ArrayList<T>>,
        func: () -> Unit
    ): MyRefreshRecyclerView {
        loadFun = func
        recyclerView!!.addLoading(func)
        autoRefresh()
        observeData(mutableLiveData)
        return this
    }

    fun loadData(
        func: () -> Unit
    ): MyRefreshRecyclerView {
        loadFun = func
        recyclerView!!.addLoading(func)
        autoRefresh()
        return this
    }

    public fun <D : MyRecyclerViewModel> addData(list: ArrayList<D>?) {
        if (list != null) {
            if (pageIndex == defaultPageIndex) {
                recyclerView!!.clearData<MyRecyclerViewModel>()
            }
            recyclerView!!.addData(list)

            emptyLayout!!.show(recyclerView!!.getData<MyRecyclerViewModel>().size == 0)
            recyclerView!!.show(recyclerView!!.getData<MyRecyclerViewModel>().size > 0)

            this.setEnableLoadMore(list.size >= pageSize)
        }
        stop()
    }

    public fun stop() {
        this.finishRefresh()
        this.finishLoadMore()
        recyclerView!!.finishLoading()
        pageIndex =
            recyclerView!!.getData<MyRecyclerViewModel>().size / pageSize + defaultPageIndex
        Log.i("MyDog", "加载更多,pageIndex:$pageIndex")
    }

    private fun <T : MyRecyclerViewModel> observeData(mutableLiveData: MutableLiveData<ArrayList<T>>) {
        mutableLiveData.observe(this.context as LifecycleOwner, Observer<ArrayList<T>> {
            this.addData(it)
        })
    }

}