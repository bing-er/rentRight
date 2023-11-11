package my.bcit.rentright.Views.Activity.place

import com.google.android.gms.maps.model.LatLng

data class Place(
    val latLng: LatLng,
    val address: String,
    val description: String,
    val rent: Float
)
