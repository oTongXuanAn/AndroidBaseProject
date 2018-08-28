package an.xuan.tong.baseproject.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.widget.ImageView


fun View.visible() {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
}

fun View.inVisible() {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
}

fun View.gone() {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
}

fun Any.dp2Pixel(c: Context, dp: Float): Float {
    val density = c.resources.displayMetrics.density
    return dp * density
}

fun Any.pixel2Dp(c: Context, pixel: Float): Float {
    val density = c.resources.displayMetrics.density
    return pixel / density
}

fun getScreenWidth(): Int {
    return Resources.getSystem().displayMetrics.widthPixels
}

fun getScreenHeight(): Int {
    return Resources.getSystem().displayMetrics.heightPixels
}

fun Context.getBitmapFromView(v: View): Bitmap {
    v.isDrawingCacheEnabled = true
    val bitmap = Bitmap.createBitmap(v.drawingCache)
    v.isDrawingCacheEnabled = false
    return bitmap
}

fun ImageView.getBitmapOfImageView(): Bitmap? = try {
    (drawable as BitmapDrawable).bitmap
} catch (ex: Exception) {
    ex.printStackTrace()
    null
}