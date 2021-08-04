package dev.davidvarela.social.domain

import dev.davidvarela.social.domain.entities.PostPreview
import dev.davidvarela.social.domain.entities.User

const val EMAIL = "username@domain.com"
const val NUM_COMMENTS = 5
val FAKE_POST_PREVIEW = PostPreview("title", 1, 1, "body")
val POSTS = listOf(FAKE_POST_PREVIEW)
val USER = User(1, "username", EMAIL, "imageUrl")
