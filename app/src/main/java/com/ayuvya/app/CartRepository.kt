package com.ayuvya.app

import androidx.lifecycle.MutableLiveData

object CartRepository {
    // This LiveData holds the shared list of cart items.
    val cartItems: MutableLiveData<MutableList<CartItem>> = MutableLiveData(mutableListOf())
}
