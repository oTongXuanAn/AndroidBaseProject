package an.xuan.tong.baseproject.ui

import an.xuan.tong.baseproject.utils.getScreenWidth
import android.support.v7.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseDialog(context: Context) : AlertDialog.Builder(context, 0), DialogInterface.OnDismissListener {
    companion object {
        val RATIO_WIDTH_DIALOG_VS_WIDTH_SCREEN = 0.78f
        var WIDTH_DIALOG = getScreenWidth() * RATIO_WIDTH_DIALOG_VS_WIDTH_SCREEN
    }

    var mDialog: android.support.v7.app.AlertDialog

    init {
        val view = LayoutInflater.from(context).inflate(initLayoutResource(), null)
        view?.let {
            setView(view)
            onViewCreated(view)
        }
        mDialog = create()
        mDialog.setOnDismissListener(this)
    }

    abstract fun initLayoutResource(): Int

    abstract fun onViewCreated(view: View)

    fun dismiss() {
        mDialog.dismiss()
    }

    override fun onDismiss(dialog: DialogInterface?) {

    }

    fun forceUserCannotDismiss() {
        mDialog.setCanceledOnTouchOutside(false)
        mDialog.setCancelable(false)
        setOnKeyListener { dialog, keyCode, event -> false }
    }

    override fun show(): AlertDialog? {
        mDialog.show()
        mDialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        mDialog.window.setLayout(WIDTH_DIALOG.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
        return null
    }
}