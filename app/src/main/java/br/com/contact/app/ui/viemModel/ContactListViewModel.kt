package br.com.contact.app.ui.viemModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.contact.domain.entity.Contact
import br.com.contact.domain.repository.ContactRepository
import br.com.contact.resources.extension.doInBackground

class ContactListViewModel(private val repository: ContactRepository) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    private val _contacts = MutableLiveData<List<Contact>>()
    private val _error = MutableLiveData<Throwable>()

    val loading: LiveData<Boolean> get() = _loading
    val contacts: LiveData<List<Contact>> get() = _contacts
    val error: LiveData<Throwable> get() = _error

    fun findAll() {
        doInBackground {
            try {
                _loading.postValue(true)
                _contacts.postValue(repository.findAll())
            } catch (e: Throwable) {
                _error.postValue(e)
            } finally {
                _loading.postValue(false)
            }
        }
    }

}