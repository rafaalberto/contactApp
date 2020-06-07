package br.com.contact.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import br.com.contact.R
import br.com.contact.app.ui.adapter.ContactListAdapter
import br.com.contact.databinding.FragmentFavoriteContactsBinding

class FavoriteContactsFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteContactsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite_contacts, container, false)
        binding.recyclerView.adapter =
            ContactListAdapter()
        return binding.root
    }
}
