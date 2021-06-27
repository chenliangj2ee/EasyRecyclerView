package com.ktl.mvvm

import android.os.Handler
import android.os.Looper
import com.ktl.mvvm.act.Bean
import com.ktl.mvvm.model.Product

object API {
    /**
     * 获取商品列表
     */
    fun getProductList(callback: Callback<ArrayList<Product>>) {

        var num = 0;
        var handler = object : Handler(Looper.getMainLooper()) {}
        handler.postDelayed(Runnable {
            var count = 0
            if (count == 0) {
                var users = ArrayList<Product>()
                users.add(
                    Product(
                        "苹果",
                        "12元一斤",
                        "https://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E9%A6%99%E8%95%89&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=1942294853,1651669&os=3942015713,3607347661&simid=3136533929,3702889490&pn=2&rn=1&di=135190&ln=1656&fr=&fmq=1617259721255_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&hs=2&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%253A%252F%252Fku.90sjimg.com%252Felement_origin_min_pic%252F16%252F11%252F29%252Fd95d445d4d300452ea8f114af7f20136.jpg%26refer%3Dhttp%253A%252F%252Fku.90sjimg.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Djpeg%3Fsec%3D1619851720%26t%3De35a8d1a1049980ce7287f1c04d7b31b&rpstart=0&rpnum=0&adpicid=0&force=undefined",
                        "日本",
                        "来自日本富士苹果来自日本富士苹果来自日本富士苹果来自日本富士苹果来自日本富士苹果",
                        0
                    )
                )
                users.add(
                    Product(
                        "西瓜",
                        "20元一斤",
                        "https://img0.baidu.com/it/u=1448927748,3561590545&fm=26&fmt=auto&gp=0.jpg",
                        "马来西亚",
                        "非常甜，口味酸中带有甜味",
                        1
                    )
                )
                users.add(
                    Product(
                        "南瓜",
                        "30元一斤",
                        "https://img1.baidu.com/it/u=2622416991,1252942741&fm=26&fmt=auto&gp=0.jpg",
                        "广西",
                        "个头大，耐存储，适合小孩",
                        1
                    )
                )
                users.add(
                    Product(
                        "苹果",
                        "12元一斤",
                        "https://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E9%A6%99%E8%95%89&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=1942294853,1651669&os=3942015713,3607347661&simid=3136533929,3702889490&pn=2&rn=1&di=135190&ln=1656&fr=&fmq=1617259721255_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&hs=2&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%253A%252F%252Fku.90sjimg.com%252Felement_origin_min_pic%252F16%252F11%252F29%252Fd95d445d4d300452ea8f114af7f20136.jpg%26refer%3Dhttp%253A%252F%252Fku.90sjimg.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Djpeg%3Fsec%3D1619851720%26t%3De35a8d1a1049980ce7287f1c04d7b31b&rpstart=0&rpnum=0&adpicid=0&force=undefined",
                        "日本",
                        "来自日本富士苹果来自日本富士苹果来自日本富士苹果来自日本富士苹果来自日本富士苹果",
                        0
                    )
                )
                users.add(
                    Product(
                        "西瓜",
                        "20元一斤",
                        "https://img0.baidu.com/it/u=1448927748,3561590545&fm=26&fmt=auto&gp=0.jpg",
                        "马来西亚",
                        "非常甜，口味酸中带有甜味",
                        1
                    )
                )
                users.add(
                    Product(
                        "南瓜",
                        "30元一斤",
                        "https://img1.baidu.com/it/u=2622416991,1252942741&fm=26&fmt=auto&gp=0.jpg",
                        "广西",
                        "个头大，耐存储，适合小孩",
                        1
                    )
                )
                users.add(
                    Product(
                        "苹果",
                        "12元一斤",
                        "https://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E9%A6%99%E8%95%89&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=1942294853,1651669&os=3942015713,3607347661&simid=3136533929,3702889490&pn=2&rn=1&di=135190&ln=1656&fr=&fmq=1617259721255_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&hs=2&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%253A%252F%252Fku.90sjimg.com%252Felement_origin_min_pic%252F16%252F11%252F29%252Fd95d445d4d300452ea8f114af7f20136.jpg%26refer%3Dhttp%253A%252F%252Fku.90sjimg.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Djpeg%3Fsec%3D1619851720%26t%3De35a8d1a1049980ce7287f1c04d7b31b&rpstart=0&rpnum=0&adpicid=0&force=undefined",
                        "日本",
                        "来自日本富士苹果来自日本富士苹果来自日本富士苹果来自日本富士苹果来自日本富士苹果",
                        0
                    )
                )
                users.add(
                    Product(
                        "西瓜",
                        "20元一斤",
                        "https://img0.baidu.com/it/u=1448927748,3561590545&fm=26&fmt=auto&gp=0.jpg",
                        "马来西亚",
                        "非常甜，口味酸中带有甜味",
                        1
                    )
                )
                users.add(
                    Product(
                        "南瓜",
                        "30元一斤",
                        "https://img1.baidu.com/it/u=2622416991,1252942741&fm=26&fmt=auto&gp=0.jpg",
                        "广西",
                        "个头大，耐存储，适合小孩",
                        1
                    )
                )
                users.add(
                    Product(
                        "苹果",
                        "12元一斤",
                        "https://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E9%A6%99%E8%95%89&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=1942294853,1651669&os=3942015713,3607347661&simid=3136533929,3702889490&pn=2&rn=1&di=135190&ln=1656&fr=&fmq=1617259721255_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&hs=2&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%253A%252F%252Fku.90sjimg.com%252Felement_origin_min_pic%252F16%252F11%252F29%252Fd95d445d4d300452ea8f114af7f20136.jpg%26refer%3Dhttp%253A%252F%252Fku.90sjimg.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Djpeg%3Fsec%3D1619851720%26t%3De35a8d1a1049980ce7287f1c04d7b31b&rpstart=0&rpnum=0&adpicid=0&force=undefined",
                        "日本",
                        "来自日本富士苹果来自日本富士苹果来自日本富士苹果来自日本富士苹果来自日本富士苹果",
                        0
                    )
                )

                callback.success(users)
            } else {
                callback.failed("失败")
            }
        }, 500)


    }

    /**
     * 获取商品列表测试使用
     */
    fun getProductList2(pageIndex: Int, pageSize: Int): Bean<ArrayList<Product>> {

        var users = ArrayList<Product>()
        users.add(
            Product(
                "苹果",
                "12元一斤",
                "https://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E9%A6%99%E8%95%89&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=1942294853,1651669&os=3942015713,3607347661&simid=3136533929,3702889490&pn=2&rn=1&di=135190&ln=1656&fr=&fmq=1617259721255_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&hs=2&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%253A%252F%252Fku.90sjimg.com%252Felement_origin_min_pic%252F16%252F11%252F29%252Fd95d445d4d300452ea8f114af7f20136.jpg%26refer%3Dhttp%253A%252F%252Fku.90sjimg.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Djpeg%3Fsec%3D1619851720%26t%3De35a8d1a1049980ce7287f1c04d7b31b&rpstart=0&rpnum=0&adpicid=0&force=undefined",
                "日本",
                "来自日本富士苹果来自日本富士苹果来自日本富士苹果来自日本富士苹果来自日本富士苹果",
                0
            )
        )
        users.add(
            Product(
                "西瓜",
                "20元一斤",
                "https://img0.baidu.com/it/u=1448927748,3561590545&fm=26&fmt=auto&gp=0.jpg",
                "马来西亚",
                "非常甜，口味酸中带有甜味",
                1
            )
        )
        users.add(
            Product(
                "南瓜",
                "30元一斤",
                "https://img1.baidu.com/it/u=2622416991,1252942741&fm=26&fmt=auto&gp=0.jpg",
                "广西",
                "个头大，耐存储，适合小孩",
                1
            )
        )
        users.add(
            Product(
                "苹果",
                "12元一斤",
                "https://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E9%A6%99%E8%95%89&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=1942294853,1651669&os=3942015713,3607347661&simid=3136533929,3702889490&pn=2&rn=1&di=135190&ln=1656&fr=&fmq=1617259721255_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&hs=2&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%253A%252F%252Fku.90sjimg.com%252Felement_origin_min_pic%252F16%252F11%252F29%252Fd95d445d4d300452ea8f114af7f20136.jpg%26refer%3Dhttp%253A%252F%252Fku.90sjimg.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Djpeg%3Fsec%3D1619851720%26t%3De35a8d1a1049980ce7287f1c04d7b31b&rpstart=0&rpnum=0&adpicid=0&force=undefined",
                "日本",
                "来自日本富士苹果来自日本富士苹果来自日本富士苹果来自日本富士苹果来自日本富士苹果",
                0
            )
        )
        users.add(
            Product(
                "西瓜",
                "20元一斤",
                "https://img0.baidu.com/it/u=1448927748,3561590545&fm=26&fmt=auto&gp=0.jpg",
                "马来西亚",
                "非常甜，口味酸中带有甜味",
                1
            )
        )
        users.add(
            Product(
                "南瓜",
                "30元一斤",
                "https://img1.baidu.com/it/u=2622416991,1252942741&fm=26&fmt=auto&gp=0.jpg",
                "广西",
                "个头大，耐存储，适合小孩",
                1
            )
        )
        users.add(
            Product(
                "苹果",
                "12元一斤",
                "https://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E9%A6%99%E8%95%89&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=1942294853,1651669&os=3942015713,3607347661&simid=3136533929,3702889490&pn=2&rn=1&di=135190&ln=1656&fr=&fmq=1617259721255_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&hs=2&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%253A%252F%252Fku.90sjimg.com%252Felement_origin_min_pic%252F16%252F11%252F29%252Fd95d445d4d300452ea8f114af7f20136.jpg%26refer%3Dhttp%253A%252F%252Fku.90sjimg.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Djpeg%3Fsec%3D1619851720%26t%3De35a8d1a1049980ce7287f1c04d7b31b&rpstart=0&rpnum=0&adpicid=0&force=undefined",
                "日本",
                "来自日本富士苹果来自日本富士苹果来自日本富士苹果来自日本富士苹果来自日本富士苹果",
                0
            )
        )
        users.add(
            Product(
                "西瓜",
                "20元一斤",
                "https://img0.baidu.com/it/u=1448927748,3561590545&fm=26&fmt=auto&gp=0.jpg",
                "马来西亚",
                "非常甜，口味酸中带有甜味",
                1
            )
        )
        users.add(
            Product(
                "南瓜",
                "30元一斤",
                "https://img1.baidu.com/it/u=2622416991,1252942741&fm=26&fmt=auto&gp=0.jpg",
                "广西",
                "个头大，耐存储，适合小孩",
                1
            )
        )
        users.add(
            Product(
                "苹果",
                "12元一斤",
                "https://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E9%A6%99%E8%95%89&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=1942294853,1651669&os=3942015713,3607347661&simid=3136533929,3702889490&pn=2&rn=1&di=135190&ln=1656&fr=&fmq=1617259721255_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&hs=2&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%253A%252F%252Fku.90sjimg.com%252Felement_origin_min_pic%252F16%252F11%252F29%252Fd95d445d4d300452ea8f114af7f20136.jpg%26refer%3Dhttp%253A%252F%252Fku.90sjimg.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Djpeg%3Fsec%3D1619851720%26t%3De35a8d1a1049980ce7287f1c04d7b31b&rpstart=0&rpnum=0&adpicid=0&force=undefined",
                "日本",
                "来自日本富士苹果来自日本富士苹果来自日本富士苹果来自日本富士苹果来自日本富士苹果",
                0
            )
        )

        var bean = Bean<ArrayList<Product>>()
        bean.code=0
        bean.data = users
        return bean

    }


}