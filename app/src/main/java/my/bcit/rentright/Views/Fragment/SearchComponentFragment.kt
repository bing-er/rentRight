package my.bcit.rentright.Views.Fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import my.bcit.rentright.R


class SearchComponentFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_component, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = listOf("City", "Price", "Type")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)

        val autoCompleteTextView = view.findViewById<AutoCompleteTextView>(R.id.actv_search_param)
        autoCompleteTextView.setAdapter(adapter)
    }


}
