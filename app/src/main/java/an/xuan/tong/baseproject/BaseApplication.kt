package an.xuan.tong.baseproject

import android.app.Activity
import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.Build
import android.os.Bundle
import android.support.multidex.MultiDexApplication
import com.squareup.picasso.LruCache
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import io.realm.Realm

class BaseApplication :  MultiDexApplication(), Application.ActivityLifecycleCallbacks{

    override fun onCreate() {
        super.onCreate()
        // Config Picasso
        Realm.init(this)
        initPicasso()

    }

    override fun onActivityPaused(activity: Activity?) {
        throw UnsupportedOperationException()
    }

    override fun onActivityResumed(activity: Activity?) {
        throw UnsupportedOperationException()
    }

    override fun onActivityStarted(activity: Activity?) {
        throw UnsupportedOperationException()
    }

    override fun onActivityDestroyed(activity: Activity?) {
        throw UnsupportedOperationException()
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        throw UnsupportedOperationException()
    }

    override fun onActivityStopped(activity: Activity?) {
        throw UnsupportedOperationException()
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        throw UnsupportedOperationException()
    }

    fun calculateMemoryCacheSize(context: Context, rate: Int = 7): Int {
        val am = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        val largeHeap = context.applicationInfo.flags and ApplicationInfo.FLAG_LARGE_HEAP != 0
        var memoryClass = am.memoryClass
        if (largeHeap && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            memoryClass = am.largeMemoryClass
        }
        // Target 15 (= 100 / rate) % of the available heap.
        return 1024 * 1024 * memoryClass / rate
    }
    /**
     * Config Picasso
     * Cache with Memory and Disk
     */
    private fun initPicasso() {
        val picassoBuilder = Picasso.Builder(this)
        picassoBuilder.downloader(OkHttp3Downloader(this, calculateMemoryCacheSize(this, 2).toLong()))
        picassoBuilder.memoryCache(LruCache(calculateMemoryCacheSize(this, 2)))
        Picasso.setSingletonInstance(picassoBuilder.build())
    }

}
