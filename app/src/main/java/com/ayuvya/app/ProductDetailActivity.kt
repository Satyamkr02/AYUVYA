package com.ayuvya.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ayuvya.app.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve product details from the Intent extras.
        val productId = intent.getStringExtra("product_id")
        val productName = intent.getStringExtra("product_name")
        val productPrice = intent.getStringExtra("product_price")
        val productDescription = intent.getStringExtra("product_description")
        val productImageResId = intent.getIntExtra("product_imageResId", 0)

        // Set product details to the views.
        binding.detailProductName.text = productName
        binding.detailProductPrice.text = "₹$productPrice"
        binding.detailProductDescription.text = productDescription
        binding.detailProductImage.setImageResource(productImageResId)
    }
}
