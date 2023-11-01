package my.bcit.rentright.Utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import my.bcit.rentright.Views.Activity.Login

class GetReady {

    fun showView(view : View) {
        view.visibility = View.VISIBLE
    }
    fun hideView(view : View) {
        view.visibility = View.GONE
    }

    fun GoToActivityLogin(context: Context, Activity : Activity) {
        val intent = Intent(context, Login::class.java)
        context.startActivity(intent)
        Activity.finish()
    }
//    fun GoToActivityNavigation(context: Context, Activity : Activity) {
//        val intent = Intent(context, Navigation::class.java)
//        context.startActivity(intent)
//        Activity.finish()
//    }

}