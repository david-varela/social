package dev.davidvarela.social.domain

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val throwable: Throwable) : Result<Nothing>()

    companion object {
        inline infix fun <T> from(f: () -> T): Result<T> =
            try {
                Success(f())
            } catch (t: Throwable) {
                Error(t)
            }
    }
}
