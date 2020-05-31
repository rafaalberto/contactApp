package br.com.contact.resources.repository

import br.com.contact.domain.entity.Contact
import br.com.contact.domain.repository.ContactRepository
import br.com.contact.resources.database.dao.FavoriteContactDao
import br.com.contact.resources.database.entity.toDomain
import br.com.contact.resources.database.entity.toEntity
import br.com.contact.resources.extension.runAsync
import br.com.contact.resources.network.ContactApi
import br.com.contact.resources.network.entity.toDomain

class ContactRepositoryImpl(
    private val api: ContactApi,
    private val dao: FavoriteContactDao
) : ContactRepository {

    override fun findAll(): List<Contact> {
        return runAsync {
            api.findAll().map { it.toDomain() }
        }
    }

    override fun findFavorites(): List<Contact> {
        return runAsync {
            dao.findAll().map { it.toDomain() }
        }
    }

    override fun findById(id: Long): Contact? {
        return runAsync {
            val contact = api.findById(id)
            contact?.toDomain()?.copy(favoriteId = dao.findByContactId(contact.id)?.id)
        }
    }

    override fun addFavorite(contact: Contact): Contact {
        return runAsync {
            when (dao.findByContactId(contact.id)) {
                null -> {
                    val favoriteId = dao.insert(contact.toEntity())
                    contact.copy(favoriteId = favoriteId)
                }
                else -> {
                    dao.delete(contact.toEntity())
                    contact.copy(favoriteId = null)
                }
            }
        }
    }
}