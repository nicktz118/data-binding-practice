package com.nicktz.minimercari

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.nicktz.minimercari.data.ProductTab
import com.nicktz.minimercari.products.ProductsFragment

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private val pagerAdapter by lazy {
        GeneralPagerAdapter(supportFragmentManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Launch Camera", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        viewModel.tabs.observe(this, Observer(::setupViewPager))

        viewModel.error.observe(this,
            Observer {
                viewPager.visibility = View.GONE
                errorTextView.visibility = View.VISIBLE
            })

    }

    private fun setupViewPager(tabs: List<ProductTab>) {
        errorTextView.visibility = View.GONE
        viewPager.visibility = View.VISIBLE
        tabs.forEach { tab ->
           pagerAdapter.addFrag(ProductsFragment.newInstance(tab), tab.name)
        }
        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }
}
