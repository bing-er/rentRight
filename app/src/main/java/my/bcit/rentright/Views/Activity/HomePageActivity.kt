package my.bcit.rentright.Views.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import my.bcit.rentright.Models.Listing.ListingResponse
import my.bcit.rentright.R
import my.bcit.rentright.Views.Fragment.FavFragment
import my.bcit.rentright.Views.Fragment.HomeFragment
import my.bcit.rentright.Views.Fragment.NotificationFragment
import my.bcit.rentright.Views.Fragment.ProfileFragment
import my.bcit.rentright.Views.Fragment.SearchFragment

class HomePageActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        val bundle = intent.extras


        bottomNavigationView = findViewById(R.id.bottom_navigation)

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

                R.id.nav_notifications -> {
                    selectedFragment = NotificationFragment()
                }

                R.id.nav_search -> {
                    selectedFragment = SearchFragment()
                }

                R.id.nav_fav -> {
                    selectedFragment = FavFragment()
                }

                R.id.nav_profile -> {
                    selectedFragment = ProfileFragment()
                }
            }
            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.container, selectedFragment!!)
            transaction.commit()
            true
        }

    }
}