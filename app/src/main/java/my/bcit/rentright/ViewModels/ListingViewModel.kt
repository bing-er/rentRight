package my.bcit.rentright.ViewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import my.bcit.rentright.Models.Listing.ListingResponse
import my.bcit.rentright.Network.ListingAPI
import androidx.lifecycle.ViewModel
import my.bcit.rentright.Network.RentRightRetrofit
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class ListingViewModel: ViewModel() {

    private var retrofit: Retrofit? = RentRightRetrofit.getInstance()
    private val service: ListingAPI? = retrofit?.create(ListingAPI::class.java)
    val listings: MutableLiveData<List<ListingResponse>?> = MutableLiveData()

    init {
        getAllListings()
    }

    private fun getAllListings(){
        viewModelScope.launch {
            try {
                val response = service?.getAllListings()
                if (response!!.isSuccessful) {
                    listings.postValue(response!!.body())
                } else {
                    listings.postValue(null)
                    Log.e("ListingViewModel", "Error fetching listings: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {

                listings.postValue(null)
                Log.e("ListingViewModel", "Exception fetching listings", e)
            }
        }
    }


    fun getAllListings(context: Context) {




    }
}