package ort.argentina.yatay.tp3.tp3_parcial_grupal3.core.utils

/**
 * GENERICS - Clases genéricas útiles
 */

/**
 * Pair genérico (alternativa a kotlin.Pair con nombres semánticos)
 */
data class Pair<out A, out B>(
    val first: A,
    val second: B
)

/**
 * Triple genérico
 */
data class Triple<out A, out B, out C>(
    val first: A,
    val second: B,
    val third: C
)

/**
 * Optional genérico (similar a Java Optional)
 */
sealed class Optional<out T> {
    data class Some<out T>(val value: T) : Optional<T>()
    object None : Optional<Nothing>()

    fun getOrNull(): T? = when (this) {
        is Some -> value
        is None -> null
    }

    fun getOrElse(default: () -> @UnsafeVariance T): @UnsafeVariance T = when (this) {
        is Some -> value
        is None -> default()
    }

    inline fun <R> map(transform: (T) -> R): Optional<R> = when (this) {
        is Some -> Some(transform(value))
        is None -> None
    }
}

/**
 * Either genérico - representa un valor que puede ser Left o Right
 * Comúnmente usado para representar Error (Left) o Success (Right)
 */
sealed class Either<out L, out R> {
    data class Left<out L>(val value: L) : Either<L, Nothing>()
    data class Right<out R>(val value: R) : Either<Nothing, R>()

    val isLeft: Boolean get() = this is Left
    val isRight: Boolean get() = this is Right

    fun getLeftOrNull(): L? = (this as? Left)?.value
    fun getRightOrNull(): R? = (this as? Right)?.value

    inline fun <T> fold(
        ifLeft: (L) -> T,
        ifRight: (R) -> T
    ): T = when (this) {
        is Left -> ifLeft(value)
        is Right -> ifRight(value)
    }

    inline fun <T> map(transform: (R) -> T): Either<L, T> = when (this) {
        is Left -> Left(value)
        is Right -> Right(transform(value))
    }
}

/**
 * Validación genérica
 */
sealed class Validation<out E, out T> {
    data class Valid<out T>(val value: T) : Validation<Nothing, T>()
    data class Invalid<out E>(val errors: List<E>) : Validation<E, Nothing>()

    val isValid: Boolean get() = this is Valid
    val isInvalid: Boolean get() = this is Invalid
}

/**
 * Lista paginada genérica
 */
data class PagedList<T>(
    val items: List<T>,
    val page: Int,
    val pageSize: Int,
    val totalItems: Int,
    val hasMore: Boolean
) {
    val totalPages: Int get() = (totalItems + pageSize - 1) / pageSize
    val isFirstPage: Boolean get() = page == 1
    val isLastPage: Boolean get() = !hasMore
}

