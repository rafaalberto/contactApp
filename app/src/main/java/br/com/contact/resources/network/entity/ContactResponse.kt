package br.com.contact.resources.network.entity

import br.com.contact.domain.entity.Contact

data class ContactResponse(
    val id: Long,
    val name: String,
    val email: String,
    val phone: String
)

fun ContactResponse.toDomain() = Contact(
    id = this.id,
    name = this.name,
    email = this.email,
    phone = this.phone
)