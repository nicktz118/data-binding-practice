package com.nicktz.minimercari.util

import androidx.recyclerview.widget.DiffUtil

@Suppress("UNCHECKED_CAST")
interface Diffable<in T : Diffable<T>> {
    fun isSameItem(item: T): Boolean = true
    fun isSameContent(item: T): Boolean = item == this
    fun getChangePayload(item: T): Any? {
        return null
    }

    @Deprecated("internal api don't use", ReplaceWith("isSameItem(item)"))
    fun isSameItemAny(item: Any): Boolean = isSameItem(item as T)

    @Deprecated("internal api don't use", ReplaceWith("isSameContent(item)"))
    fun isSameContentAny(item: Any): Boolean = isSameContent(item as T)

    @Deprecated("internal api don't use", ReplaceWith("getChangePayload(item)"))
    fun getChangePayloadAny(item: Any): Any? = getChangePayload(item as T)
}

class DiffableCallabck(
    private val oldItems: List<Diffable<*>>,
    private val newItems: List<Diffable<*>>
) : DiffUtil.Callback() {

    private val oldIndexedValuesGroupByClass = oldItems.withIndex().groupBy { it.value::class }
    private val newIndexedValuesGroupByClass = newItems.withIndex().groupBy { it.value::class }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        if (oldItem::class != newItem::class) {
            return false
        }
        val isSameItem = oldItem.isSameItemAny(newItem)
        return run {
            if (isSameItem) {
                if (oldItemPosition == newItemPosition) true
                else {
                    val oldIndex = oldIndexedValuesGroupByClass.getValue(oldItem::class)
                        .indexOfFirst { it.index == oldItemPosition }
                    val newIndex = newIndexedValuesGroupByClass.getValue(newItem::class)
                        .indexOfFirst { it.index == newItemPosition }
                    oldIndex == newIndex
                }
            } else false
        }
    }

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return oldItem.isSameContentAny(newItem)
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return oldItem.getChangePayloadAny(newItem)
    }
}