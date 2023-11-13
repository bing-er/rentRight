package my.bcit.rentright.Views.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import my.bcit.rentright.Models.Listing.Listing
import my.bcit.rentright.R
import my.bcit.rentright.Views.Fragment.ListingDetailFragment

class ListingDetailActivity : AppCompatActivity() {
    private lateinit var listing :Listing
    private lateinit var rent : TextView
    private lateinit var address : TextView
    private lateinit var city : TextView
    private lateinit var state : TextView
    private lateinit var zipcode : TextView
    private lateinit var image : ImageView
    private lateinit var backBtn: ImageButton
    private lateinit var shareBtn: ImageButton
    private lateinit var callBtn: Button
    private lateinit var emailBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing_detail)

        init()
        val bundle = intent.extras?.getBundle("listing")

        val listingJson = bundle?.getString("listing_json")
        val gson = Gson()
        val listingType = object : TypeToken<Listing>() {}.type
        listing = listingJson?.let { json ->
            gson.fromJson(json, listingType)}!!
    }



    private fun init() {
        rent = findViewById(R.id.rentTextView)
        address =findViewById(R.id.addressTextView)
        city  = findViewById(R.id.cityTextView)
        state = findViewById(R.id.stateTextView)
        zipcode = findViewById(R.id.zipcodeTextView)
        image  = findViewById(R.id.imageView)
        backBtn = findViewById(R.id.backButton)
        shareBtn = findViewById(R.id.shareButton)
        callBtn = findViewById(R.id.callButton)
        emailBtn = findViewById(R.id.emailButton)
    }
}