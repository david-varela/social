package dev.davidvarela.social.domain.entities

import java.io.Serializable

data class PostPreview(val title: String, val postId: Int, val userId: Int, val body: String) :
    Serializable

