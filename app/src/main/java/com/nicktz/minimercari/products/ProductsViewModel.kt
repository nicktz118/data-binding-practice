package com.nicktz.minimercari.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nicktz.minimercari.BaseViewModel
import com.nicktz.minimercari.api.ProductClient
import com.nicktz.minimercari.data.Product
import com.nicktz.minimercari.data.ProductTab
import io.reactivex.rxkotlin.subscribeBy

class ProductsViewModel(private val productTab: ProductTab?,
                        private val productClient: ProductClient) : BaseViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> get() = _success

    init {
        productTab?.let { tab ->
            _success.value = true
            fetchProducts(tab)
        } ?: run {
            _success.value = false
        }
    }

    private fun fetchProducts(tab: ProductTab) {
        launch {
            productClient.getProductsByUrl(tab.url)
                .subscribeBy(
                    onSuccess = {
                        _success.value = true
                        _products.value = it
                    },
                    onError = {
                        _success.value = false
                    }
                )
        }
    }
}