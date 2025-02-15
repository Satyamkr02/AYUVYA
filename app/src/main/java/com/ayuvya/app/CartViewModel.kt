package com.ayuvya.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class CartViewModel : ViewModel() {
    // Use the shared LiveData from CartRepository.
    val cartItems: LiveData<MutableList<CartItem>> = CartRepository.cartItems

    fun addProduct(product: Product) {
        val list = CartRepository.cartItems.value ?: mutableListOf()
        val existingItem = list.find { it.product.id == product.id }
        if (existingItem != null) {
            existingItem.quantity++
        } else {
            list.add(CartItem(product, 1))
        }
        // Update the LiveData so observers are notified.
        CartRepository.cartItems.value = list
    }

    fun removeProduct(product: Product) {
        val list = CartRepository.cartItems.value ?: mutableListOf()
        val existingItem = list.find { it.product.id == product.id }
        if (existingItem != null) {
            if (existingItem.quantity > 1) {
                existingItem.quantity--
            } else {
                list.remove(existingItem)
            }
        }
        CartRepository.cartItems.value = list
    }

    fun removeProductCompletely(product: Product) {
        val list = CartRepository.cartItems.value ?: mutableListOf()
        list.removeAll { it.product.id == product.id }
        CartRepository.cartItems.value = list
    }

    fun getTotalItems(): Int {
        return CartRepository.cartItems.value?.sumOf { it.quantity } ?: 0
    }
}
