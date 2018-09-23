package an.xuan.tong.baseproject.ui

import an.xuan.tong.baseproject.R
import android.app.Activity
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

open class BaseActivity : AppCompatActivity() {


    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.no_anim, R.anim.slide_out_right)
        Log.e("antx", "test1")
        Log.e("antx", "test1")
        Log.e("antx", "test1")
    }

    fun hideKeyboard(activity: Activity) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        if (imm.isAcceptingText) {
            val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
        }
        Log.e("antx", "test1")
    }

    override fun onResume() {
        super.onResume()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    override fun onPause() {
        super.onPause()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

}