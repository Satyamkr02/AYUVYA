package com.ayuvya.app

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ayuvya.app.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding

    // Get an instance of CartViewModel for this activity.
    // With a shared repository setup, this will update the same cart data.
    private val cartViewModel: CartViewModel by viewModels()

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

        // Set up the Add to Cart button functionality.
        binding.buttonAddToCart.setOnClickListener {
            val product = Product(
                id = productId ?: "",
                name = productName ?: "",
                price = productPrice ?: "",
                description = productDescription ?: "",
                imageResId = productImageResId
            )
            cartViewModel.addProduct(product)
            Toast.makeText(this, "$productName added to cart", Toast.LENGTH_SHORT).show()

            // Navigate back to the home screen by finishing this activity.
            finish()
        }

        //
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
