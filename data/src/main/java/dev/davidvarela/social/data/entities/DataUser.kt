package dev.davidvarela.social.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import dev.davidvarela.social.data.BuildConfig
import dev.davidvarela.social.domain.entities.User

// Same model for remote and local data sources because of the simplicity of the problem, two are
// not necessary in this case
@Entity
@JsonClass(generateAdapter = true)
data class DataUser(@PrimaryKey val id: Int, val username: String, val email: String) {
    fun toUser(): User = User(id, username, email, "${BuildConfig.IMAGES_BASE_URL}$email.png")
}
