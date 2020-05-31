package br.com.contact.resources.network

import br.com.contact.resources.network.entity.ContactResponse

interface ContactApi {

    fun findAll(): List<ContactResponse>
    fun findById(id: Long): ContactResponse?

}