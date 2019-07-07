package com.nicktz.minimercari.products


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

import com.nicktz.minimercari.R
import com.nicktz.minimercari.data.ProductTab
import com.nicktz.minimercari.databinding.FragmentProductsBinding
import com.nicktz.minimercari.util.SimpleDecoration
import kotlinx.android.synthetic.main.fragment_products.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ProductsFragment : Fragment() {

    private val productsAdapter by lazy { ProductsAdapter() }

    private val decoration by lazy {
        SimpleDecoration(resources.getDimensionPixelOffset(R.dimen.item_margin), 2)
    }

    private var toast: Toast? = null

    private var productTab: ProductTab? = null

    private val viewModel: ProductsViewModel by viewModel{ parametersOf(productTab) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productTab = parseProductTab(arguments)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentProductsBinding>(
            inflater,
            R.layout.fragment_products,
            container,
            false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.productRecyclerView.apply {
            adapter = productsAdapter
            addItemDecoration(decoration)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productRecyclerView.visibility = View.VISIBLE

        viewModel
            .products
            .observe(this, Observer(productsAdapter::setDiffableItems))

        productsAdapter
            .clickEvent
            .observe(this, Observer { showToast("Choose ${it.name}") })
    }

    private fun showToast(message: String) {
        toast?.cancel()
        toast = Toast.makeText(
            requireActivity(),
            message, Toast.LENGTH_SHORT
        ).also { it.show() }
    }

    companion object {
        private const val BUNDLE_KEY_PRODUCT_TAB = "bundle_key_product_tab"
        private fun parseProductTab(args: Bundle?): ProductTab? =
            args?.getParcelable(BUNDLE_KEY_PRODUCT_TAB)

        fun newInstance(productTab: ProductTab): ProductsFragment {
            return ProductsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(BUNDLE_KEY_PRODUCT_TAB, productTab)
                }
            }
        }
    }
}
