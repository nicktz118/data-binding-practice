package com.nicktz.minimercari.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nicktz.minimercari.R
import com.nicktz.minimercari.data.Product
import com.nicktz.minimercari.databinding.ItemProductBinding
import com.nicktz.minimercari.util.Diffable
import com.nicktz.minimercari.util.DiffableCallabck

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    private var items: List<Product> = listOf()
    private val _clickEvent = MutableLiveData<Product>()
    val clickEvent: LiveData<Product> get() = _clickEvent

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = DataBindingUtil.inflate<ItemProductBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_product,
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    fun setDiffableItems(items: List<Diffable<Product>>) {
        val result = items

        if (result == this.items) {
            return
        }

        if (this.items.isEmpty()) {
            this.items = result as List<Product>
            notifyDataSetChanged()
        } else {
            val diffableCallabck = DiffableCallabck(this.items as List<Diffable<*>>, result)
            this.items = result as List<Product>
            DiffUtil.calculateDiff(diffableCallabck).dispatchUpdatesTo(this)
        }
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.onBind(items[position], _clickEvent)
    }

    override fun getItemCount(): Int = items.size

    class ProductViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Product, _clickEvent: MutableLiveData<Product>) {
            binding.product = data
            binding.setClickEvent { _clickEvent.postValue(data) }
        }

    }

    companion object UserInfoDiff : DiffUtil.ItemCallback<Product>() {

        override fun areItemsTheSame(oldItem: Product, newItem: Product) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem
    }

}