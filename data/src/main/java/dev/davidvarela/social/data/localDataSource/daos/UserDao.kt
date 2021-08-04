package dev.davidvarela.social.data.localDataSource.daos

import androidx.room.Dao
import androidx.room.Query
import dev.davidvarela.social.data.entities.DataUser

@Dao
interface UserDao : BaseDao<DataUser> {
    @Query("SELECT * FROM datauser WHERE id = :userId ")
    fun read(userId: Int): DataUser?
}
