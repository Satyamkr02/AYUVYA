package com.ayuvya.app

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ayuvya.app.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var cartBadge: TextView
    private lateinit var cartIconWithBadge: FrameLayout
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var fragmentTopHeadingText: TextView

    // Shared CartViewModel instance.
    private val cartViewModel: CartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cartBadge = binding.cartBadge
        cartIconWithBadge = binding.cartIconWithBadge
        bottomNavigationView = binding.bottomNavigation
        fragmentTopHeadingText = binding.FragmentTopHeadingText

        fragmentTopHeadingText.text = "All Products"
        loadFragment(HomeFragment())

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    fragmentTopHeadingText.text = "All Products"
                    loadFragment(HomeFragment())
                }
                R.id.nav_cart -> {
                    fragmentTopHeadingText.text = "Your Shopping Cart"
                    loadFragment(CartFragment())
                }
                R.id.nav_profile -> {
                    fragmentTopHeadingText.text = "Profile"
                    loadFragment(ProfileFragment())
                }
            }
            true
        }

        cartIconWithBadge.setOnClickListener {
            navigateToCartFragment()
        }

        cartViewModel.cartItems.observe(this) {
            val total = cartViewModel.getTotalItems()
            cartBadge.text = total.toString()
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    // Public function to navigate to the CartFragment.
    fun navigateToCartFragment() {
        fragmentTopHeadingText.text = "Cart"
        loadFragment(CartFragment())
        bottomNavigationView.selectedItemId = R.id.nav_cart
    }
}
