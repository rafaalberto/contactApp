package br.com.contact.app.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.contact.R
import br.com.contact.app.ui.activity.AddFavoriteActivity
import br.com.contact.app.ui.adapter.ContactListAdapter
import br.com.contact.app.ui.viemModel.ContactListViewModel
import br.com.contact.databinding.FragmentContactListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactListFragment : Fragment() {

    companion object {
        const val CONTACT_ID = "contact-id"
    }

    private val viewModel by viewModel<ContactListViewModel>()

    private lateinit var binding: FragmentContactListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contact_list, container, false)

        binding.swipeLayout.setOnRefreshListener { viewModel.findAll() }

        binding.recyclerView.adapter = ContactListAdapter(this::redirectFavoriteContact)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        observeLoading()
        observeContacts()
        observeError()
        viewModel.findAll()
    }

    private fun redirectFavoriteContact(contactId: Int) {
        val intent = Intent(activity, AddFavoriteActivity::class.java)
        intent.putExtra(CONTACT_ID, contactId)
        startActivity(intent)
    }

    private fun observeLoading() {
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            binding.swipeLayout.isRefreshing = it
        })
    }

    private fun observeContacts() {
        viewModel.contacts.observe(viewLifecycleOwner, Observer {
            (binding.recyclerView.adapter as ContactListAdapter).updateContacts(it)
        })
    }

    private fun observeError() {
        viewModel.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, R.string.error_loading_contacts, Toast.LENGTH_SHORT).show()
        })
    }

}
