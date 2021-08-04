package dev.davidvarela.social.domain.entities

data class User(
    val id: Int, // not really necessary for this demo
    val username: String,
    val email: String,
    val imageUrl: String
)
