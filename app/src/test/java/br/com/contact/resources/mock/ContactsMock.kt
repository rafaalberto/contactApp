package br.com.contact.resources.mock

import br.com.contact.resources.database.entity.FavoriteContactEntity
import br.com.contact.resources.network.entity.ContactResponse

object ContactsMock {

    val contactResponse =
        ContactResponse(id = 1, name = "Contact 1", email = "contact1@email.com", phone = "123")

    val contactsResponse = listOf(
        ContactResponse(id = 2, name = "Contact 2", email = "contact2@email.com", phone = "123"),
        ContactResponse(id = 3, name = "Contact 3", email = "contact3@email.com", phone = "456"),
        ContactResponse(id = 4, name = "Contact 4", email = "contact4@email.com", phone = "789"),
        ContactResponse(id = 5, name = "Contact 5", email = "contact5@email.com", phone = "222"),
        ContactResponse(id = 6, name = "Contact 6", email = "contact6@email.com", phone = "333")
    )

    val favoriteContact = FavoriteContactEntity(id = 1, name = "Contact 1", email = "contact1@email.com", phone = "123", contactId = 1)

}