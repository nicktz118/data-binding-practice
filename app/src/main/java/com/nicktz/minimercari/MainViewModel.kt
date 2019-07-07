package com.nicktz.minimercari

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nicktz.minimercari.api.ProductClient
import com.nicktz.minimercari.data.ProductTab
import io.reactivex.rxkotlin.subscribeBy

class MainViewModel(private val productClient: ProductClient) : BaseViewModel() {

    private val _tabs = MutableLiveData<List<ProductTab>>()
    val tabs: LiveData<List<ProductTab>> get() = _tabs

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> get() = _error

    init {
        launch {
            productClient
                .getProductTabs()
                .subscribeBy(
                    onSuccess = { tabs ->
                        _tabs.value = tabs
                    },
                    onError = { throwable ->
                        _error.value = throwable
                    }
                )
        }
    }
}