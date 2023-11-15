package my.bcit.rentright.Views.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import kotlinx.coroutines.launch
import my.bcit.rentright.Models.Listing.ListingResponse
import my.bcit.rentright.Models.User
import my.bcit.rentright.R
import my.bcit.rentright.Utils.CustomToast
import my.bcit.rentright.ViewModels.ListingViewModel
import my.bcit.rentright.ViewModels.UserViewModel
import my.bcit.rentright.Views.Fragment.FavFragment
import my.bcit.rentright.Views.Fragment.HomeFragment
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
            Log.i("listings number ", listings?.size.toString())
            listings?.let {
                updateUIWithListings(it)
            } ?: run {
                CustomToast(this, "Sorry, Something Goes Wrong!", "RED").show()
            }
        }
        setupBottomNav()

//        userViewModel.currentUser.observe(this) { user ->
//            if (user != null) {
//                setupBottomNavigation(user, bundle)
//            } else {
//                // 用户未登录，跳转到登录界面
//                val intent = Intent(this@HomePageActivity, Login::class.java)
//                startActivity(intent)
//            }
//        }


        supportFragmentManager.beginTransaction().replace(R.id.container, HomeFragment()
            .apply{
                arguments = bundle
            }).commit();

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

    private fun setupBottomNav() {
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> switchToFragment(HomeFragment())
                R.id.nav_refresh -> listingViewModel.refreshListings()
                R.id.nav_search -> switchToFragment(SearchFragment())
                R.id.nav_fav, R.id.nav_profile -> {
                    userViewModel.currentUser.observe(this) { user ->
                        if (user != null) {

                            val fragment =
                                if (item.itemId == R.id.nav_fav) FavFragment() else ProfileFragment()
                            switchToFragment(fragment)
                        } else {

                            navigateToLogin()
                        }

                        userViewModel.currentUser.removeObservers(this)
                    }
                }
            }
            true
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this@HomePageActivity, Login::class.java)
        startActivity(intent)
    }

    private fun switchToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

//    private fun setupBottomNavigation(user:User, bundle:Bundle?) {
//        bottomNavigationView.setOnItemSelectedListener { item ->
//            var selectedFragment: Fragment? = null
//            when (item.itemId) {
//                R.id.nav_home -> {
//                    selectedFragment = HomeFragment().apply {
//                        arguments = bundle
//                    }
//                }
//
//                R.id.nav_refresh -> {
//                    listingViewModel.refreshListings()
//                }
//
//                R.id.nav_search -> {
//                    selectedFragment = SearchFragment()
//                }
//
//                R.id.nav_fav -> {
//                    selectedFragment =
//
////                    lifecycleScope.launch {
////                        val user = userViewModel.getCurrentUser()
////                        if (user != null) {
////
////                            selectedFragment = FavFragment()
////
////                        } else {
////                            val intent = Intent(this@HomePageActivity, Login::class.java)
////                            startActivity(intent)
////                        }
////                    }
//
//                }
//
//                R.id.nav_profile -> {
//                    lifecycleScope.launch {
//                        val user = userViewModel.getCurrentUser()
//                        if (user != null) {
//
//                            selectedFragment = FavFragment()
//
//                        } else {
//                            val intent = Intent(this@HomePageActivity, Login::class.java)
//                            startActivity(intent)
//                        }
//                    }
//                }
//            }
//            selectedFragment?.let{
//                val transaction = supportFragmentManager.beginTransaction()
//                transaction.replace(R.id.container, selectedFragment!!)
//                transaction.commit()
//
//            }
//
//            true
//        }
//
//    }

    }
