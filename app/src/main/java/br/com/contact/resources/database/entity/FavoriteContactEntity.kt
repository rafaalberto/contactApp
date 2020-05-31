package br.com.contact.resources.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.contact.domain.entity.Contact

@Entity(tableName = "favorite_contacts")
data class FavoriteContactEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,

    val name: String,

    val email: String,

    val phone: String,

    @ColumnInfo(name = "contact_id")
    val contactId: Long

)

fun FavoriteContactEntity.toDomain() = Contact(
    id = this.contactId,
    name = this.name,
    email = this.email,
    phone = this.phone,
    favoriteId = this.id
)

fun Contact.toEntity() = FavoriteContactEntity(
    name = this.name,
    email = this.email,
    phone = this.phone,
    contactId = this.id
)

