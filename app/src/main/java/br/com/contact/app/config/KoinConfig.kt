package br.com.contact.app.config

import androidx.room.Room
import br.com.contact.app.ui.viemModel.ContactListViewModel
import br.com.contact.app.ui.viemModel.FavoriteContactsViewModel
import br.com.contact.domain.repository.ContactRepository
import br.com.contact.resources.database.AppDatabase
import br.com.contact.resources.network.ContactApi
import br.com.contact.resources.network.fuel.ContactFuelApi
import br.com.contact.resources.repository.ContactRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }
    single { get<AppDatabase>().favoriteContactDao() }
}

val networkModule = module {
    single<ContactApi> { ContactFuelApi() }
}

val repositoryModule = module {
    single<ContactRepository> { ContactRepositoryImpl(get(), get()) }
}

val viewModelModule = module {
    viewModel { ContactListViewModel(get()) }
    viewModel { FavoriteContactsViewModel(get()) }
}