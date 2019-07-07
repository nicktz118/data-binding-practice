package com.nicktz.minimercari.di

import com.nicktz.minimercari.Constants
import com.nicktz.minimercari.MainViewModel
import com.nicktz.minimercari.api.ProductClient
import com.nicktz.minimercari.api.ProductService
import com.nicktz.minimercari.data.ProductTab
import com.nicktz.minimercari.products.ProductsViewModel
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { (productTab: ProductTab?) -> ProductsViewModel(productTab, get()) }
}

val repositoryModule = module {
}

val networkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideProductService(get()) }
    single { provideProductClient(get()) }

}

fun provideOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .build()

fun provideRetrofit(client: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()

fun provideProductService(retrofit: Retrofit): ProductService =
    retrofit.create(ProductService::class.java)

fun provideProductClient(productService: ProductService): ProductClient =
    ProductClient(productService)

val appModule = listOf(viewModelModule, networkModule, repositoryModule)