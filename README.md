# 使用步骤： 
第一步：再proje build中添加：

    	allprojects {
		repositories {
			...
		       maven { url 'https://jitpack.io' }
		}
	}

第二部：再module中添加：

    dependencies {
	       implementation 'com.github.chenliangj2ee:EasyRecyclerView:1.2.0'
	} 


# EasyRecyclerView

### 通过Kotlin语言，基于MVVM模式，通过ViewBinding，ViewModel，LiveData技术，实现了RecyclerView最精简封装，2行代码搞定，什么下拉刷新，加载更多，分页算法，创建adapter，关联listData，数据为空时自定义emptyView的显示，都可以不用再去关心了，【目前只适用与列表为单一type类型，下拉刷新使用SmartRefreshLayout库，默认使用linear布局，可以通过 refresh.recyclerView实现对RecyclerView操作】



### Activity继承自MyBaseActivity【也可以继承自己定义的】如下，ActivityRecycleviewBinding为R.layout.activity_recycleview布局自动生成的Binding： 

    class RecyclerViewActivity : MyBaseActivity<ActivityRecycleviewBinding, PruductListViewModel>() {

    override fun layoutId(): Int {
        return R.layout.activity_recycleview;
    }

    //创建自定义的ViewModel class
    override fun initViewModelClass(): Class<PruductListViewModel> {
        return PruductListViewModel::class.java
    }

    override fun initCreate() {
       //第一步：refresh.bindData方法，绑定item与model ，it.key.product对应item布局里声明的variable变量，固定写法：it.key.xxx=it.value
       //第二部：refresh.loadData方法，下拉刷新，上拉加载都调用该方法：viewModel.products为MutableLiveData类型数据集合，分页必须使用refresh.pageIndex,  refresh.pageSize参数
       //**************** ***********核心代码*************************************
        refresh.bindData<ItemProductBinding, Product> { it.key.product = it.value }
        refresh.loadData(viewModel.products){  viewModel.getProducts(  refresh.pageIndex,  refresh.pageSize ) }
       //**************** ***********核心代码*************************************
    }

}
## 对应R.layout.activity_recycleview（ActivityRecycleviewBinding）布局， 
### app:item="@layout/item_product"：指定item布局， 
### app:empty_layout="@layout/layout_empty"：指定列表数据为null时显示的布局。

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
     

## 对应的item布局：
<?xml version="1.0" encoding="utf-8"?>
   <layout xmlns:android="http://schemas.android.com/apk/res/android"
  >
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
## 对应ViewModel

class PruductListViewModel : ViewModel() {

    var products = MutableLiveData<ArrayList<Product>>()
    
    fun getProducts(pageIndex: Int, pageSize: Int) {
        //模拟网络访问
        handler.postDelayed(Runnable {
            var results = ArrayList<Product>()
            results.add(Product("苹果",  "12元一斤",   "", "日本",  "来自日本富士苹果 ",  0))
            results.add(Product("苹果",  "12元一斤",   "", "日本",  "来自日本富士苹果 ",  0))
            results.add(Product("苹果",  "12元一斤",   "", "日本",  "来自日本富士苹果 ",  0))
            products.value = results

        }, 1000)

    }
}
# 请资助我一个棒棒糖吧，在此感谢：


<img width="406" alt="微信图片_20210609173434" src="https://user-images.githubusercontent.com/4067327/121332592-989b2780-c94a-11eb-9543-a4e00db3b759.png">


