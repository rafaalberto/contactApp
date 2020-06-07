package br.com.contact.app.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.contact.R
import br.com.contact.app.ui.fragment.ContactListFragment
import br.com.contact.app.ui.fragment.FavoriteContactsFragment
import br.com.contact.app.ui.adapter.FragmentPage
import br.com.contact.app.ui.adapter.ViewPagerAdapter
import br.com.contact.databinding.ActivityContactListBinding

class ContactListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact_list)
        setTabs()
    }

    private fun setTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addPage(FragmentPage(ContactListFragment(), getString(R.string.tab_contact)))
        adapter.addPage(FragmentPage(FavoriteContactsFragment(), getString(R.string.tab_favorites)))
        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }

}