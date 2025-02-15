package com.ayuvya.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(
    private val cartItems: List<CartItem>,
    private val onIncreaseQuantity: (CartItem) -> Unit,
    private val onDecreaseQuantity: (CartItem) -> Unit,
    private val onDeleteProduct: (CartItem) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.cart_product_image)
        val productName: TextView = itemView.findViewById(R.id.cart_product_name)
        val productPrice: TextView = itemView.findViewById(R.id.cart_product_price)
        val quantityText: TextView = itemView.findViewById(R.id.cart_product_quantity)
        val increaseButton: Button = itemView.findViewById(R.id.button_increase)
        val decreaseButton: Button = itemView.findViewById(R.id.button_decrease)
        val deleteButton: Button = itemView.findViewById(R.id.delete_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart_product, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartItems[position]
        // Use the product from cartItem to set the image.
        // If imageResId is nullable, provide a default placeholder image.
        holder.productImage.setImageResource(cartItem.product.imageResId ?: R.drawable.placeholder_image)
        holder.productName.text = cartItem.product.name
        holder.productPrice.text = "₹${cartItem.product.price}"
        holder.quantityText.text = cartItem.quantity.toString()

        holder.increaseButton.setOnClickListener {
            onIncreaseQuantity(cartItem)
        }
        holder.decreaseButton.setOnClickListener {
            onDecreaseQuantity(cartItem)
        }
        holder.deleteButton.setOnClickListener {
            onDeleteProduct(cartItem)
            Toast.makeText(holder.itemView.context, "${cartItem.product.name} removed from cart", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = cartItems.size
}
