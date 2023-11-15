package my.bcit.rentright.ViewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import my.bcit.rentright.Network.ListingAPI
import androidx.lifecycle.ViewModel
import my.bcit.rentright.Network.RentRightRetrofit
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import my.bcit.rentright.Models.Listing.ListingResponse
import my.bcit.rentright.Utils.CustomToast
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListingViewModel: ViewModel() {

    private var retrofit: Retrofit? = RentRightRetrofit.getInstance()
    private val service: ListingAPI? = retrofit?.create(ListingAPI::class.java)
    val allListings: MutableLiveData<List<ListingResponse>?> = MutableLiveData()
    val searchListingsResult: MutableLiveData<List<ListingResponse>?> = MutableLiveData()
    private val _closeDetailEvent = MutableLiveData<Boolean?>()

    init {
        getAllListings()
    }

    val closeDetailEvent: MutableLiveData<Boolean?>
        get() = _closeDetailEvent

    // Method to call from the SearchComponentFragment when you want to close the detail fragment
    fun onCloseDetailRequested() {
        _closeDetailEvent.value = true
    }

    fun onCloseDetailRequestedComplete() {
        _closeDetailEvent.value = null
    }

    private fun getAllListings() {
        viewModelScope.launch {
            try {
                val response = service?.getAllListings()
                if (response!!.isSuccessful) {
                    allListings.postValue(response!!.body())
                } else {
                    allListings.postValue(null)
                    Log.e(
                        "ListingViewModel",
                        "Error fetching listings: ${response.errorBody()?.string()}"
                    )
                }
            } catch (e: Exception) {

                allListings.postValue(null)
                Log.e("ListingViewModel", "Exception fetching listings", e)
            }
        }
    }


    fun getAllListings(context:Context) {


    }

    fun refreshListings() {
        getAllListings()
    }


    fun searchListing(searchField: String, searchValue: String, context: Context) {
        val searchCriteria = JsonObject().apply {
            addProperty(searchField, searchValue)
        }

        service?.searchListings(searchCriteria)?.enqueue(object : Callback<List<ListingResponse>> {
            override fun onResponse(
                call: Call<List<ListingResponse>>,
                response: Response<List<ListingResponse>>
            ) {
                if (response.isSuccessful) {
                    Log.i("ListingViewModel", "Network call successful")
                    val listingResponse = response.body() ?: emptyList()

                    searchListingsResult.postValue(listingResponse)
                   // Log.i("search result", listingResponse[0].category.toString())
                    //Log.i("searchListingResult", searchListingsResult.value?.get(0)?.category.toString())


                } else {
                    searchListingsResult.postValue(emptyList())
                    Log.e("SearchFragment", "Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<ListingResponse>>, t: Throwable) {

               CustomToast(context, "Something wrong with the network", "red")
            }
        })


    }
}