package dev.davidvarela.social.domain.useCases

import com.google.common.truth.Truth.assertThat
import dev.davidvarela.social.domain.POSTS
import dev.davidvarela.social.domain.Result
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test

class PostsUseCaseTest {

    @Test
    fun postsUseCase_emit_success() = runBlocking {
        val useCase = PostsUseCase(SuccessFakeRepository())
        val result = useCase().first()
        assertThat(result).isInstanceOf(Result.Success::class.java)
        assertThat((result as Result.Success).data).isInstanceOf(List::class.java)
        assertThat(result.data).hasSize(POSTS.size)
        assertThat(result.data).isEqualTo(POSTS)
    }

    @Test
    fun postsUseCase_emit_error() = runBlocking {
        val useCase = PostsUseCase(ErrorFakeRepository())
        val result = useCase().first()
        assertThat(result).isInstanceOf(Result.Error::class.java)
        assertThat((result as Result.Error).throwable).isInstanceOf(RuntimeException::class.java)
    }
}
