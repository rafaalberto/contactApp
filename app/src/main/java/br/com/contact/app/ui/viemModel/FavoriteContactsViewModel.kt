package br.com.contact.app.ui.viemModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.contact.domain.entity.Contact
import br.com.contact.domain.repository.ContactRepository
import br.com.contact.resources.extension.doInBackground

class FavoriteContactsViewModel(private val repository: ContactRepository) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    private val _favorites = MutableLiveData<List<Contact>>()
    private val _error = MutableLiveData<Throwable>()

    val loading: LiveData<Boolean> get() = _loading
    val favorites: LiveData<List<Contact>> get() = _favorites
    val error: LiveData<Throwable> get() = _error

    fun findFavorites() {
        doInBackground {
            try {
                _loading.postValue(true)
                _favorites.postValue(repository.findFavorites())
            } catch (e: Throwable) {
                _error.postValue(e)
                e.message?.let { Log.e("favorites()", it) }
            } finally {
                _loading.postValue(false)
            }
        }
    }

}