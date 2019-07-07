package com.nicktz.minimercari.api

import com.nicktz.minimercari.data.Product
import com.nicktz.minimercari.data.ProductTab
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ProductService {

    @GET("master.json")
    fun getProductTabs(): Single<List<ProductTab>>

    @GET
    fun getProductByUrl(@Url url: String): Single<List<Product>>
}