package dev.davidvarela.social.domain.useCases

import com.google.common.truth.Truth.assertThat
import dev.davidvarela.social.domain.NUM_COMMENTS
import dev.davidvarela.social.domain.Result
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test

class NumCommentsUseCaseTest {
    @Test
    fun numCommentsUseCase_emit_success() = runBlocking {
        val useCase = NumCommentsUseCase(SuccessFakeRepository())
        val result = useCase(1).first()
        assertThat(result).isInstanceOf(Result.Success::class.java)
        assertThat((result as Result.Success).data).isEqualTo(NUM_COMMENTS)
    }

    @Test
    fun numCommentsUseCase_emit_error() = runBlocking {
        val useCase = NumCommentsUseCase(ErrorFakeRepository())
        val result = useCase(1).first()
        assertThat(result).isInstanceOf(Result.Error::class.java)
        assertThat((result as Result.Error).throwable).isInstanceOf(RuntimeException::class.java)
    }
}
