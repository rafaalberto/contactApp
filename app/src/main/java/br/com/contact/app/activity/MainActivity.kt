package br.com.contact.app.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.contact.BuildConfig
import br.com.contact.R
import br.com.contact.app.config.databaseModule
import br.com.contact.app.config.networkModule
import br.com.contact.app.config.repositoryModule
import com.github.kittinunf.fuel.core.FuelManager
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
