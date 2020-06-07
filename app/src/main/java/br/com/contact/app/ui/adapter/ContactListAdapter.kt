package br.com.contact.app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.contact.R
import br.com.contact.databinding.ContactItemBinding
import br.com.contact.domain.entity.Contact

class ContactListAdapter(private val onItemClick: (Int) -> Unit = {}) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val contacts = mutableListOf<Contact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ContactListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        )
    }

    override fun getItemCount() = contacts.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val contact = contacts[position]

        val binding = (holder as ContactListViewHolder).binding

        binding.contact = contact

        binding.layout.setOnClickListener { onItemClick(contact.id.toInt()) }
    }

    fun updateContacts(contacts: List<Contact>) {
        this.contacts.clear()
        this.contacts.addAll(contacts)
        this.notifyDataSetChanged()
    }

}

private class ContactListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding: ContactItemBinding = ContactItemBinding.bind(view)
}