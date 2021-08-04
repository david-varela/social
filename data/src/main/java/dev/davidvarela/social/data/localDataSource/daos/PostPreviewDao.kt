package dev.davidvarela.social.data.localDataSource.daos

import androidx.room.Dao
import androidx.room.Query
import dev.davidvarela.social.data.entities.DataPostPreview

@Dao
interface PostPreviewDao : BaseDao<DataPostPreview> {
    @Query("SELECT * FROM datapostpreview ORDER BY postId")
    fun readAll(): List<DataPostPreview>
}
