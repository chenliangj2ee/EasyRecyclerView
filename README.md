# EasyRecyclerView
基于MVVM实现了数据RecyclerView or ListView列表展示最精简封装，2行代码搞定，什么下拉刷新，加载更多，分页算法，创建adapter，关联listData，数据为空时自定义emptyView的显示，都可以不用再去关心了


Activity继承：MyBaseActivity如下，ActivityRecycleviewBinding为R.layout.activity_recycleview布局对应Binding：
class RecyclerViewActivity : MyBaseActivity<ActivityRecycleviewBinding, PruductListViewModel>() {

    override fun layoutId(): Int {
        return R.layout.activity_recycleview;
    }

    //创建自定义的ViewModel class
    override fun initViewModelClass(): Class<PruductListViewModel> {
        return PruductListViewModel::class.java
    }

    override fun initCreate() {

       //第一步：绑定item与model ，binding.product对应item布局里声明的variable变量
        refresh.bindData { binding: ItemProductBinding, bean: Product ->
            binding.product = bean; 
        }
        //第二部：下拉刷新，上拉加载都调用该方法，分页必须使用refresh.pageIndex,  refresh.pageSize参数
        refresh.loadData(viewModel.products) {
               viewModel.getProducts(  refresh.pageIndex,  refresh.pageSize )
        }
    }

}
对应R.layout.activity_recycleview（ActivityRecycleviewBinding）布局，  
app:item="@layout/item_product"指定item布局，
app:empty_layout="@layout/layout_empty"指定列表数据为null时显示的布局：

<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <com.chenliang.library.view.MyRefreshRecyclerView
        android:id="@+id/refresh"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:empty_layout="@layout/layout_empty"
        app:item="@layout/item_product" />

</layout>

对应的item布局：
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">
    <data>
        <variable
            name="product"
            type="com.ktl.mvvm.model.Product" />
    </data>
     
    <LinearLayout
        android:layout_width="0dp"
        android:layout_height="match_parent"
        android:layout_weight="1"
        android:orientation="vertical">
        <TextView
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_weight="1"
            android:text="@{product.name}" />

        <TextView
             android:layout_width="match_parent"
             android:layout_height="0dp"
             android:layout_weight="1"
             android:text="@{product.price}" />
      </LinearLayout>
 
</layout> 

