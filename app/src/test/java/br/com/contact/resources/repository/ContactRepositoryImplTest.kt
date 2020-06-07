package br.com.contact.resources.repository

import br.com.contact.resources.database.dao.FavoriteContactDao
import br.com.contact.resources.mock.ContactsMock.contactResponse
import br.com.contact.resources.mock.ContactsMock.contactsResponse
import br.com.contact.resources.mock.ContactsMock.favoriteContact
import br.com.contact.resources.network.ContactApi
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Test

class ContactRepositoryImplTest {

    private val api = mockk<ContactApi>()
    private val dao = mockk<FavoriteContactDao>()
    private val repository = ContactRepositoryImpl(api, dao)

    @Test
    fun `find all contacts with no results`() {
        every { api.findAll() } returns emptyList()

        val result = repository.findAll()

        verify { api.findAll() }

        assertTrue(result.isEmpty())
    }

    @Test
    fun `find all contacts with one result`() {
        every { api.findAll() } returns listOf(contactResponse)

        val result = repository.findAll()

        verify { api.findAll() }

        assertFalse(result.isEmpty())
    }

    @Test
    fun `find all contacts with multiple result`() {
        every { api.findAll() } returns contactsResponse

        val result = repository.findAll()

        verify { api.findAll() }

        assertTrue(result.size == 5)
    }

    @Test
    fun `find nonexistent contact by id`() {
        every { api.findById(any()) } returns null

        val result = repository.findById(1)

        verify { api.findById(any()) }

        assertNull(result)
    }

    @Test
    fun `find favorite contact by id`() {
        every { api.findById(any()) } returns contactResponse

        every { dao.findByContactId(contactResponse.id) } returns favoriteContact

        val result = repository.findById(1)

        verify { api.findById(any()) }

        assertNotNull(result)
    }

}