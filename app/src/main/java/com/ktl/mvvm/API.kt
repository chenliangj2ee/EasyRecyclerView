package com.ktl.mvvm

import com.ktl.mvvm.act.Bean
import com.ktl.mvvm.model.Product

object API {
    /**
     * 获取商品列表测试使用
     */
    fun getProductList2(pageIndex: Int, pageSize: Int): Bean<ArrayList<Product>> {

        var results = ArrayList<Product>()

        for (i in 1..20) {
            var p = Product()
            p.name = "苹果$i"
            p.des = "好吃的苹果，吃了腰不酸"
            p.from = "广西"
            p.price = "12"
            p.src =
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fstatic.ankangwang.com%2Fwen%2F201812%2F100612%2F01.jpg&refer=http%3A%2F%2Fstatic.ankangwang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1627829127&t=4a83244ff998837808e0dbe1d87aca7e"
            p.state = 0
            p.itemType = (Math.random() * 3).toInt()
            results.add(p)
        }
        var bean = Bean<ArrayList<Product>>()
        bean.code = 0
        bean.data = results
        return bean

    }


}