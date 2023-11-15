package my.bcit.rentright.Views.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import my.bcit.rentright.Models.Listing.Listing
import my.bcit.rentright.Models.Listing.ListingResponse
import my.bcit.rentright.R
import my.bcit.rentright.Utils.CustomToast
import my.bcit.rentright.ViewModels.ListingViewModel
import my.bcit.rentright.ViewModels.UserViewModel
import my.bcit.rentright.Views.Fragment.FavFragment
import my.bcit.rentright.Views.Fragment.HomeFragment
import my.bcit.rentright.Views.Fragment.NotificationFragment
import my.bcit.rentright.Views.Fragment.ProfileFragment
import my.bcit.rentright.Views.Fragment.SearchFragment

class HomePageActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private val listingViewModel: ListingViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        val bundle = intent.extras
        bottomNavigationView = findViewById(R.id.bottom_navigation)

        listingViewModel.allListings.observe(this) { listings ->
            listings?.let {
                updateUIWithListings(it)
            } ?: run {
                CustomToast(this, "Sorry, Something Goes Wrong!", "RED").show()
            }
        }


        supportFragmentManager.beginTransaction().replace(R.id.container, HomeFragment()
            .apply{
                arguments = bundle
            }).commit();

        bottomNavigationView.setOnItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.nav_home -> {
                    selectedFragment = HomeFragment().apply {
                        arguments = bundle
                    }
                }

                R.id.nav_refresh -> {
                    listingViewModel.refreshListings()
                }

                R.id.nav_search -> {
                    selectedFragment = SearchFragment()
                }

                R.id.nav_fav -> {
                    userViewModel.getCurrentUser(this, this)
                    selectedFragment = FavFragment()
                }

                R.id.nav_profile -> {
                    userViewModel.getCurrentUser(this, this)
                    selectedFragment = ProfileFragment()
                }
            }
            selectedFragment?.let{
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.container, selectedFragment!!)
                transaction.commit()

            }

            true
        }

    }

    private fun updateUIWithListings(listings: List<ListingResponse>) {
        val bundle = Bundle().apply {
            val gson = Gson()
            val listingsJson = gson.toJson(listings)
            putString("listings_json", listingsJson)
        }

        val transaction = supportFragmentManager.beginTransaction()
        val homeFragment = HomeFragment().apply {
            arguments = bundle
        }
        transaction.replace(R.id.container, homeFragment)
        transaction.commit()
    }

    }
