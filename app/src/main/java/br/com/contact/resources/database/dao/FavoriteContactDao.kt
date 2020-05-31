package br.com.contact.resources.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.contact.resources.database.entity.FavoriteContactEntity

@Dao
interface FavoriteContactDao {

    @Query(value = "SELECT * FROM favorite_contacts")
    fun findAll(): List<FavoriteContactEntity>

    @Query(
        value = """
            SELECT * FROM favorite_contacts 
            WHERE contact_id = :contactId
            """
    )
    fun findByContactId(contactId: Long): FavoriteContactEntity?

    @Insert
    fun insert(favoriteContactEntity: FavoriteContactEntity): Long

    @Delete
    fun delete(favoriteContactEntity: FavoriteContactEntity)
}