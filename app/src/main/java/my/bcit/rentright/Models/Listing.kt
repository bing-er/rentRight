package my.bcit.rentright.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Listing(
    @SerializedName("publisher")
    @Expose
    var publisher: String? = null,

    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("images")
    @Expose
    var images: List<String>? = null,

    @SerializedName("description")
    @Expose
    var description: String? = null,

    @SerializedName("location")
    @Expose
    var location: Location? = null,

    @SerializedName("category")
    @Expose
    var category: String? = null
) {
    data class Location(
        @SerializedName("lat")
        @Expose
        var lat: Double? = null,

        @SerializedName("lon")
        @Expose
        var lon: Double? = null,

        @SerializedName("address")
        @Expose
        var address: String? = null,

        @SerializedName("city")
        @Expose
        var city: String? = null,

        @SerializedName("state")
        @Expose
        var state: String? = null,

        @SerializedName("zip")
        @Expose
        var zip: String? = null
    )
}

