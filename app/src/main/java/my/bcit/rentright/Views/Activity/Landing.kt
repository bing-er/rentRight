package my.bcit.rentright.Views.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import my.bcit.rentright.R
import my.bcit.rentright.databinding.ActivityLandingBinding
import kotlinx.coroutines.*
import my.bcit.rentright.Models.Listing
import my.bcit.rentright.Network.ListingAPI
import my.bcit.rentright.Network.RentRightRetrofit
import my.bcit.rentright.Utils.CustomToast
import retrofit2.Response
import retrofit2.Retrofit
import com.google.gson.Gson

class Landing : AppCompatActivity() {

    private lateinit var binding: ActivityLandingBinding
    private lateinit var listingAPI: ListingAPI
    private lateinit var retrofit: Retrofit
    override fun onCreate(savedInstanceState: Bundle?) {
        retrofit = RentRightRetrofit.getInstance()
        listingAPI = retrofit.create(ListingAPI::class.java)
        super.onCreate(savedInstanceState)
        fetchListings(this)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val getStartedButton: Button = findViewById(R.id.getStartedButton)
        getStartedButton.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        }

    private fun fetchListings(context: Context) {
        CoroutineScope(Dispatchers.Main).launch {
            if (!::listingAPI.isInitialized) {
                listingAPI = retrofit.create(ListingAPI::class.java)
            }
            val response: Response<List<Listing>> = withContext(Dispatchers.IO) {
                listingAPI.getAllListings()
            }
            if (response.isSuccessful){
                val listings = response.body() ?: emptyList()

                navigateToHome(listings)

            } else {
                CustomToast(context, "Sorry, Something Goes Wrong!", "RED").show()

            }
        }
    }

    private fun navigateToHome(listings: List<Listing>){
        val gson = Gson()
        val listingsJson = gson.toJson(listings)
        val intent = Intent(this, HomePageActivity::class.java).apply {
            putExtra("listings_extra", listingsJson)
        }
        startActivity(intent)
        finish()
    }
    }




