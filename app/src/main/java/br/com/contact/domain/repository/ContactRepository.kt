package br.com.contact.domain.repository

import br.com.contact.domain.entity.Contact

interface ContactRepository {

    fun findAll(): List<Contact>
    fun findFavorites(): List<Contact>
    fun findById(id: Long): Contact?
    fun addFavorite(contact: Contact): Contact

}