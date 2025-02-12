package com.ayuvya.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayuvya.app.databinding.FragmentCartBinding

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private lateinit var totalAmountText: TextView
    private lateinit var placeholderImage: ImageView

    // Share the same CartViewModel with HomeFragment and MainActivity
    private val cartViewModel: CartViewModel by activityViewModels()

    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewCart.layoutManager = LinearLayoutManager(requireContext())
        totalAmountText = binding.TotalAmountText
        placeholderImage = binding.placeholderImage

        // Observe changes in cart items and update the RecyclerView and total amount
        cartViewModel.cartItems.observe(viewLifecycleOwner) { cartItems ->

            cartAdapter = CartAdapter(
                cartItems,
                onIncreaseQuantity = { cartItem ->
                    cartViewModel.addProduct(cartItem.product)
                },
                onDecreaseQuantity = { cartItem ->
                    cartViewModel.removeProduct(cartItem.product)
                },
                onDeleteProduct = { cartItem ->
                    cartViewModel.removeProductCompletely(cartItem.product)
                }
            )
            binding.recyclerViewCart.adapter = cartAdapter

            // Update total items count and total amount
            binding.textViewTotalItems.text = "Total Items in the Cart: ${cartViewModel.getTotalItems()}"
            updateTotalAmount(cartItems)

            // Handle placeholder visibility based on cart items
            if (cartItems.isEmpty()) {
                placeholderImage.visibility = View.VISIBLE
                binding.recyclerViewCart.visibility = View.GONE
            } else {
                placeholderImage.visibility = View.GONE
                binding.recyclerViewCart.visibility = View.VISIBLE
            }
        }
    }

    private fun updateTotalAmount(cartItems: List<CartItem>) {

        val totalAmount = cartItems.sumOf {
            (it.product.price.toDouble() * it.quantity).toDouble() // Convert price to Double and to ensure proper multiplication
        }
        totalAmountText.text = "₹$totalAmount"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
