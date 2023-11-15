package my.bcit.rentright.Views.Fragment

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.findFragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import my.bcit.rentright.R
import my.bcit.rentright.ViewModels.UserViewModel


class ProfileFragment : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var uName: TextView
    private lateinit var uEmail: TextView
    private lateinit var uPhone: TextView
    private lateinit var logoutBtn: Button
    private val userViewModel: UserViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUserData()

    }

    private fun setUserData() {
        sharedPreferences = requireContext().getSharedPreferences("Rentright", AppCompatActivity.MODE_PRIVATE);
        uName = requireView().findViewById(R.id.user_name)
        uEmail = requireView().findViewById(R.id.email)
        uPhone = requireView().findViewById(R.id.phone)
        logoutBtn = requireView().findViewById(R.id.btn_logout)
        setLogoutBtn(logoutBtn)
        val userName = sharedPreferences.getString("userName", "N/A")
        val userEmail = sharedPreferences.getString("userEmail", "N/A")
        val userPhone = sharedPreferences.getString("userPhone", "N/A")
        uName.text = userName
        uEmail.text = userEmail
        uPhone.text = userPhone

    }

    private fun setLogoutBtn(logoutBtn:Button) {
        logoutBtn.setOnClickListener{
            userViewModel.logout(requireContext())

        }
    }


}