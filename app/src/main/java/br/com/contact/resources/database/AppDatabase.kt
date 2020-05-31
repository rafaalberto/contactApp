package br.com.contact.resources.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.contact.resources.database.dao.FavoriteContactDao
import br.com.contact.resources.database.entity.FavoriteContactEntity

@Database(
    version = 1,
    entities = [FavoriteContactEntity::class],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteContactDao(): FavoriteContactDao

    companion object {
        const val DATABASE_NAME = "contacts-db"
    }

}