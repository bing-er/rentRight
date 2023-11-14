package my.bcit.rentright.Views.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import my.bcit.rentright.databinding.ActivityLandingBinding
import kotlinx.coroutines.*
import my.bcit.rentright.Models.Listing.ListingResponse
import my.bcit.rentright.Network.ListingAPI
import my.bcit.rentright.Network.RentRightRetrofit
import my.bcit.rentright.Utils.CustomToast
import retrofit2.Response
import retrofit2.Retrofit
import com.google.gson.Gson
import my.bcit.rentright.ViewModels.ListingViewModel

class Landing : AppCompatActivity() {

    private lateinit var binding: ActivityLandingBinding
//    private lateinit var listingAPI: ListingAPI
//    private lateinit var retrofit: Retrofit
    private lateinit var bundle: Bundle
    private val listingViewModel: ListingViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
//        retrofit = RentRightRetrofit.getInstance()
//        listingAPI = retrofit.create(ListingAPI::class.java)
        super.onCreate(savedInstanceState)
       // fetchListings(this)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listingViewModel.allListings.observe(this) { listings ->
            listings?.let{
                navigateToHome(it)
            } ?:run {
                CustomToast(this, "Sorry, Something Goes Wrong!", "RED").show()
            }

        }

        }

//    private fun fetchListings(context: Context) {
//        CoroutineScope(Dispatchers.Main).launch {
//            if (!::listingAPI.isInitialized) {
//                listingAPI = retrofit.create(ListingAPI::class.java)
//            }
//            val response: Response<List<ListingResponse>> = withContext(Dispatchers.IO) {
//                listingAPI.getAllListings()
//            }
//            if (response.isSuccessful){
//                val listings = response.body() ?: emptyList()
//
//                navigateToHome(listings)
//
//            } else {
//                CustomToast(context, "Sorry, Something Goes Wrong!", "RED").show()
//
//            }
//        }
//    }

    private fun navigateToHome(listings: List<ListingResponse>){

        bundle = Bundle().apply {
            val gson = Gson()
            val listingsJson = gson.toJson(listings)
            putString("listings_json", listingsJson)
        }
        intent = Intent(this, HomePageActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
        finish()
        }
    }




