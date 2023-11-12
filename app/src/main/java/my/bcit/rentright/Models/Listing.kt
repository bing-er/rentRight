package my.bcit.rentright.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Listing {

    @SerializedName("publisher")
    @Expose
    private var publisher: String? = null

    @SerializedName("title")
    @Expose
    private var title: String? = null

    @SerializedName("images")
    @Expose
    private var images: List<String>? = null

    @SerializedName("description")
    @Expose
    private var description: String? = null

    @SerializedName("location")
    @Expose
    private var location: Location? = null

    @SerializedName("category")
    @Expose
    private var category: String? = null

    fun getPublisher(): String? {
        return publisher
    }

    fun setPublisher(publisher: String?) {
        this.publisher = publisher
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title
    }

    fun getImages(): List<String>? {
        return images
    }

    fun setImages(images: List<String>?) {
        this.images = images
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description = description
    }

    fun getLocation(): Location? {
        return location
    }

    fun setLocation(location: Location?) {
        this.location = location
    }

    fun getCategory(): String? {
        return category
    }

    fun setCategory(category: String?) {
        this.category = category
    }

    class Location {

        @SerializedName("lat")
        @Expose
        private var lat: Double? = null

        @SerializedName("lon")
        @Expose
        private var lon: Double? = null

        @SerializedName("address")
        @Expose
        private var address: String? = null

        @SerializedName("city")
        @Expose
        private var city: String? = null

        @SerializedName("state")
        @Expose
        private var state: String? = null

        @SerializedName("zip")
        @Expose
        private var zip: String? = null

        fun getLat(): Double? {
            return lat
        }

        fun setLat(lat: Double?) {
            this.lat = lat
        }

        fun getLon(): Double? {
            return lon
        }

        fun setLon(lon: Double?) {
            this.lon = lon
        }

        fun getAddress(): String? {
            return address
        }

        fun setAddress(address: String?) {
            this.address = address
        }

        fun getCity(): String? {
            return city
        }

        fun setCity(city: String?) {
            this.city = city
        }

        fun getState(): String? {
            return state
        }

        fun setState(state: String?) {
            this.state = state
        }

        fun getZip(): String? {
            return zip
        }

        fun setZip(zip: String?) {
            this.zip = zip
        }
    }
}
