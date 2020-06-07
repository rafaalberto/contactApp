package br.com.contact.app.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(manager: FragmentManager) :
    FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val pages = mutableListOf<FragmentPage>()

    override fun getItem(position: Int): Fragment {
        return pages[position].fragment
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pages[position].title
    }

    fun addPage(page: FragmentPage) {
        pages.add(page)
    }

}

data class FragmentPage(val fragment: Fragment, val title: String)