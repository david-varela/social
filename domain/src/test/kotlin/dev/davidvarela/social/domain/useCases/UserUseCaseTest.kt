package dev.davidvarela.social.domain.useCases

import com.google.common.truth.Truth.assertThat
import dev.davidvarela.social.domain.Result
import dev.davidvarela.social.domain.USER
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test

class UserUseCaseTest {
    @Test
    fun emailUseCase_emit_success() = runBlocking {
        val useCase = UserUseCase(SuccessFakeRepository())
        val result = useCase(1).first()
        assertThat(result).isInstanceOf(Result.Success::class.java)
        assertThat((result as Result.Success).data).isEqualTo(USER)
    }

    @Test
    fun emailUseCase_emit_error() = runBlocking {
        val useCase = UserUseCase(ErrorFakeRepository())
        val result = useCase(1).first()
        assertThat(result).isInstanceOf(Result.Error::class.java)
        assertThat((result as Result.Error).throwable).isInstanceOf(RuntimeException::class.java)
    }
}
