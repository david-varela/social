package dev.davidvarela.social.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DataComment(@PrimaryKey val postId: Int, val numComments: Int)
