package br.com.contact.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.contact.R
import br.com.contact.app.ui.adapter.ContactListAdapter
import br.com.contact.app.ui.viemModel.FavoriteContactsViewModel
import br.com.contact.databinding.FragmentFavoriteContactsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteContactsFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteContactsBinding

    private val viewModel by viewModel<FavoriteContactsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite_contacts, container, false)
        binding.swipeLayout.setOnRefreshListener { viewModel.findFavorites() }
        binding.recyclerView.adapter = ContactListAdapter()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        observeLoading()
        observeFavorites()
        observeError()
        viewModel.findFavorites()
    }

    private fun observeLoading() {
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            binding.swipeLayout.isRefreshing = it
        })
    }

    private fun observeFavorites() {
        viewModel.favorites.observe(viewLifecycleOwner, Observer {
            (binding.recyclerView.adapter as ContactListAdapter).updateContacts(it)
        })
    }

    private fun observeError() {
        viewModel.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, R.string.error_loading_contacts, Toast.LENGTH_SHORT).show()
        })
    }
}
