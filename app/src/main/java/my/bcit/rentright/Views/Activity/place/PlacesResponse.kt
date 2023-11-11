package my.bcit.rentright.Views.Activity.place

import com.google.android.gms.maps.model.LatLng

data class PlaceResponse(
    val geometry: Geometry,
    val address: String,
    val description: String,
    val rent: Float
) {

    data class Geometry(
        val location: GeometryLocation
    )

    data class GeometryLocation(
        val lat: Double,
        val lng: Double
    )
}

fun PlaceResponse.toPlace(): Place = Place(
    latLng = LatLng(geometry.location.lat, geometry.location.lng),
    address = address,
    description = description,
    rent = rent
)
