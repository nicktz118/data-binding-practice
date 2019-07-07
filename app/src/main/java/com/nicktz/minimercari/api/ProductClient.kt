package com.nicktz.minimercari.api

import com.nicktz.minimercari.data.Product
import com.nicktz.minimercari.data.ProductTab
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

class ProductClient (private val productService: ProductService) {

    fun getProductTabs() : Single<List<ProductTab>> {
        return productService.getProductTabs()
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getProductsByUrl(url: String) : Single<List<Product>> {
        return productService.getProductByUrl(url)
            .observeOn(AndroidSchedulers.mainThread())
    }
}