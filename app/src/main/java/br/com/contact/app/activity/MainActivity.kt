package br.com.contact.app.activity

import android.app.Application
import br.com.contact.BuildConfig
import br.com.contact.app.config.databaseModule
import br.com.contact.app.config.networkModule
import br.com.contact.app.config.repositoryModule
import com.github.kittinunf.fuel.core.FuelManager
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainActivity : Application() {

    override fun onCreate() {
        super.onCreate()

        FuelManager.instance.basePath = BuildConfig.API_URL

        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule
                )
            )
        }
    }
}
