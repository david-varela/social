package dev.davidvarela.social.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.davidvarela.social.domain.entities.PostPreview

// Same model for remote and local data sources because of the simplicity of the problem, two are
// not necessary in this case
@Entity
@JsonClass(generateAdapter = true)
data class DataPostPreview(
    @PrimaryKey @Json(name = "id") val postId: Int,
    val userId: Int,
    val title: String,
    val body: String
) {
    fun toPostPreview(): PostPreview = PostPreview(title, postId, userId, body)
}
