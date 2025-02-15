package com.ayuvya.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(
    private val productList: List<Product>,
    private val onAddToCartClick: (Product, ProductViewHolder) -> Unit,
    private val onViewDetailsClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.product_image)
        val productName: TextView = itemView.findViewById(R.id.product_name)
        val productPrice: TextView = itemView.findViewById(R.id.product_price)
        val productDescription: TextView = itemView.findViewById(R.id.product_description)
        val viewDetailsButton: Button = itemView.findViewById(R.id.view_details_button)
        val addToCartButton: Button = itemView.findViewById(R.id.add_to_cart_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        // If product.imageResId is null, use a default placeholder image.
        holder.productImage.setImageResource(product.imageResId ?: R.drawable.placeholder_image)
        holder.productName.text = product.name
        holder.productPrice.text = "₹${product.price}"
        holder.productDescription.text = product.description
        // Hide description initially (if you wish to show it on the detail screen only)
        holder.productDescription.visibility = View.GONE

        holder.viewDetailsButton.text = "View Details"
        holder.viewDetailsButton.setOnClickListener {
            onViewDetailsClick(product)
        }

        // When Add to Cart button is clicked:
        holder.addToCartButton.text = "Add to Cart"
        holder.addToCartButton.isEnabled = true
        holder.addToCartButton.setOnClickListener {
            onAddToCartClick(product, holder)
        }
    }

    override fun getItemCount(): Int = productList.size
}
