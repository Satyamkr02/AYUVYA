package com.ayuvya.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// Data class to represent an item in the cart
data class CartItem(val product: Product, var quantity: Int)

class CartViewModel : ViewModel() {
    // MutableLiveData holding a mutable list of CartItem objects
    private val _cartItems = MutableLiveData<MutableList<CartItem>>(mutableListOf())
    val cartItems: LiveData<MutableList<CartItem>> get() = _cartItems

    // Add a product to the cart (increases quantity if it already exists)
    fun addProduct(product: Product) {
        val list = _cartItems.value ?: mutableListOf()
        val existingItem = list.find { it.product.id == product.id }
        if (existingItem != null) {
            existingItem.quantity++
        } else {
            list.add(CartItem(product, 1))
        }
        _cartItems.value = list
    }

    // Remove a product (decrease quantity or remove entirely if quantity becomes 0)
    fun removeProduct(product: Product) {
        val list = _cartItems.value ?: return
        val existingItem = list.find { it.product.id == product.id }
        if (existingItem != null) {
            if (existingItem.quantity > 1) {
                existingItem.quantity--
            } else {
                list.remove(existingItem)
            }
        }
        _cartItems.value = list
    }

    // Remove product completely from the cart
    fun removeProductCompletely(product: Product) {
        val updatedCartItems = _cartItems.value?.toMutableList() ?: mutableListOf()
        updatedCartItems.removeAll { it.product == product }
        _cartItems.value = updatedCartItems
    }

    // Returns the total count of items in the cart
    fun getTotalItems(): Int {
        return _cartItems.value?.sumOf { it.quantity } ?: 0
    }
}
