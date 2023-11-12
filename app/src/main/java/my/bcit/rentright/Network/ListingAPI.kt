package my.bcit.rentright.Network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*
import retrofit2.Response
import java.util.*
import my.bcit.rentright.Models.Listing.ListingResponse


interface ListingAPI {

    @GET("listing")
    suspend fun getAllListings(): Response<List<ListingResponse>>

    @POST("listing/search")
    fun searchListings(@Body searchCriteria:JsonObject): Call<List<ListingResponse>>


    @GET("listing/{id}")
    fun getListing(@Path("id") id: String): Call<ListingResponse>


}