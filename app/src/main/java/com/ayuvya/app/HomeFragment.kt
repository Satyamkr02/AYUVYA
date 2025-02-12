package com.ayuvya.app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayuvya.app.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var productAdapter: ProductAdapter
    private val productList = mutableListOf<Product>()

    // Shared CartViewModel instance.
    private val cartViewModel: CartViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the adapter with both callbacks.
        productAdapter = ProductAdapter(
            productList,
            onAddToCartClick = { product, holder ->
                // Add product to the cart
                cartViewModel.addProduct(product)
                Toast.makeText(requireContext(), "${product.name} added to cart", Toast.LENGTH_SHORT).show()
                // Update button UI state
                holder.addToCartButton.text = "Added"
                holder.addToCartButton.isEnabled = false
                // Navigate to the CartFragment via MainActivity
                (activity as? MainActivity)?.navigateToCartFragment()
            },
            onViewDetailsClick = { product ->
                // Navigate to ProductDetailActivity with product details passed via Intent
                val intent = Intent(requireContext(), ProductDetailActivity::class.java)
                intent.putExtra("product_id", product.id)
                intent.putExtra("product_name", product.name)
                intent.putExtra("product_price", product.price)
                intent.putExtra("product_description", product.description)
                intent.putExtra("product_imageResId", product.imageResId)
                startActivity(intent)
            }
        )

        binding.recyclerViewProducts.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = productAdapter
        }

        loadProducts()
    }

    private fun loadProducts() {
        // Sample product data
        productList.add(
            Product(
                "1",
                "Ayuvya i-Gain+",
                "749",
                "Helps in weight gain and muscle growth with Ayurvedic ingredients.",
                R.drawable.firts_image_of_igain_carousel
            )
        )
        productList.add(
            Product(
                "2",
                "Ayuvya Boobeautiful Oil",
                "599",
                "Nourishing oil for breast care with herbal extracts.",
                R.drawable.firts_image_of_boobeautiful_carousel
            )
        )
        productList.add(
            Product(
                "3",
                "Ayuvya Boomax | 60 capsules",
                "765",
                "Supports energy, stamina, and vitality naturally.",
                R.drawable.firts_image_of_boomax_carousel
            )
        )
        productList.add(
            Product(
                "4",
                "Ayuvya FizzHim | 15 Tablets",
                "499",
                "Refreshing effervescent tablets for men's health and energy boost.",
                R.drawable.firts_image_of_fizzhim_raspberry_carousel
            )
        )
        productList.add(
            Product(
                "5",
                "Ayuvya L-Rise | Libido Booster For Women",
                "579",
                "Natural supplement to enhance female libido and hormonal balance.",
                R.drawable.firts_image_of_l_rise_carousel
            )
        )
        productList.add(
            Product(
                "6",
                "Ayuvya Under Eye Gel",
                "499",
                "Reduces dark circles and puffiness with Ayurvedic extracts.",
                R.drawable.firts_image_of_undereyegel_carousel
            )
        )
        productList.add(
            Product(
                "7",
                "Ayuvya Shilajit",
                "749",
                "Pure Himalayan Shilajit Resin for strength and vitality.",
                R.drawable.shilajit_new_carousel_image_for_website
            )
        )
        productList.add(
            Product(
                "8",
                "Ayuvya Drop-it | 60 Tablets",
                "499",
                "Helps in healthy weight loss with Ayurvedic formulation.",
                R.drawable.firts_image_of_drop_it_carousel
            )
        )
        productList.add(
            Product(
                "9",
                "Ayuvya Ace-it",
                "499",
                "2-in-1 Ayurvedic formula for better nutrient absorption.",
                R.drawable.ayuvya_ace_it_32_9cd8d
            )
        )
        productList.add(
            Product(
                "10",
                "Ayuvya 24 Superfoods",
                "799",
                "A power-packed blend of 24 superfoods for daily nutrition.",
                R.drawable.superfood_combo_image_updated
            )
        )
        productList.add(
            Product(
                "11",
                "Ayuvya PCOS & PCOD Care Kit",
                "999",
                "Helps regulate hormonal balance and manage PCOS/PCOD symptoms.",
                R.drawable.varanya_niruja_first_image
            )
        )
        productList.add(
            Product(
                "12",
                "Ayuvya Piles Relief Kit",
                "899",
                "Provides pain relief and wound healing for piles.",
                R.drawable.firts_image_of_pileskit_carousel
            )
        )

        Log.d("HomeFragment", "Product list size: ${productList.size}")
        productAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
