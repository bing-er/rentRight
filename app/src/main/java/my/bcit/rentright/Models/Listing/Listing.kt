package my.bcit.rentright.Models.Listing

import com.google.android.gms.maps.model.LatLng

data class Listing(
    val publisher: String,
    val title: String,
    val rent: Int,
    val images: List<String>,
    val description: String,
    val category: String,
    val latLng: LatLng,
    val address: String,
    val city: String,
    val state: String,
    val zip: String
)

