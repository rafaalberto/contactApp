package br.com.contact.domain.entity

data class Contact(
    val id: Long,
    val name: String,
    val email: String,
    val phone: String,
    val favoriteId: Long? = null
)