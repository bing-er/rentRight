package my.bcit.rentright.Views.Fragment


import android.view.LayoutInflater
import android.view.ViewGroup
import my.bcit.rentright.R
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import my.bcit.rentright.Views.Activity.place.Place
import my.bcit.rentright.Views.Activity.place.PlacesReader


class HomeFragment : Fragment() {


    private val places: List<Place> by lazy {
        PlacesReader(requireContext()).read()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchFragment = SearchComponentFragment()
        childFragmentManager.beginTransaction()
            .add(R.id.search_fragment_container, searchFragment)
            .commit()

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync { googleMap ->
            addMarkers(googleMap)
            // Ensure all places are visible in the map.
            googleMap.setOnMapLoadedCallback {
                val bounds = LatLngBounds.builder()
                places.forEach { bounds.include(it.latLng) }
                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 100))
            }

        }


    }

    private fun addMarkers(googleMap: GoogleMap) {
        places.forEach { place ->
            googleMap.addMarker(
                MarkerOptions()
                    .title("${place.address} ($${place.rent}/Monthly)")
                    .position(place.latLng)
                    .snippet(place.description)
            )
        }
    }
}