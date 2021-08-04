package dev.davidvarela.social.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import kotlin.RuntimeException

class ResultTest {
    @Test
    fun result_from_is_Success() {
        val result = Result from { NUM_COMMENTS }
        assertThat(result).isInstanceOf(Result.Success::class.java)
        assertThat((result as Result.Success).data).isEqualTo(NUM_COMMENTS)
    }

    @Test
    fun result_from_is_Error() {
        val result = Result from { throw RuntimeException() }
        assertThat(result).isInstanceOf(Result.Error::class.java)
        assertThat((result as Result.Error).throwable).isInstanceOf(RuntimeException::class.java)
    }
}
