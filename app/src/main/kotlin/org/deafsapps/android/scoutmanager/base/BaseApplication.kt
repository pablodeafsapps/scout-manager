package org.deafsapps.android.scoutmanager.base

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * This class implements an [Application] subclass instance which serves as entry point to the app.
 * General tool configurations such as 'LeakCanary' for memory leaks, and 'Koin' for dependency
 * inversion are initialized here.
 *
 * @author Pablo L. Sordo Martínez
 * @since 1.0
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(listOf())
        }
    }

}