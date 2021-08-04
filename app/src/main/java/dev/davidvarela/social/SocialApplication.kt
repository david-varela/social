package dev.davidvarela.social

import android.app.Application
import dev.davidvarela.social.data.localDataSource.AppDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@Suppress("unused")
class SocialApplication : Application() {
    lateinit var db: AppDataBase

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SocialApplication)
            androidLogger()
            modules(appModule)
        }
    }
}
