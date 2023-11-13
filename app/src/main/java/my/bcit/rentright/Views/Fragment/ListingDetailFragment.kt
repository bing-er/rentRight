package my.bcit.rentright.Views.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import my.bcit.rentright.Models.Listing.Listing
import my.bcit.rentright.Models.Listing.ListingResponse
import my.bcit.rentright.R
import org.w3c.dom.Text

// Your custom fragment to show listing details
class ListingDetailFragment : Fragment() {

    companion object {
        private const val ARG_LISTING = "listing"

        fun newInstance(listing: Listing): ListingDetailFragment {

            val fragment = ListingDetailFragment()
            fragment.arguments = Bundle().apply{
                val gson = Gson()
                var listingJson = gson.toJson(listing)
                putString(ARG_LISTING, listingJson)
            }
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_listing_detail, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listingJson = arguments?.getString(ARG_LISTING)
        val gson = Gson()
        val listingType = object : TypeToken<Listing>() {}.type
        val listing :Listing? = listingJson?.let { json ->
            gson.fromJson(json, listingType)}

        val price: TextView = view.findViewById(R.id.listing_price)
        val address: TextView = view.findViewById(R.id.listing_address)



    }

    private fun setImage(slot: ImageView) {


    }

}
