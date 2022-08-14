package com.example.utils

sealed class DbResult<out T: Any> {
    object NotFound: DbResult<Nothing>()
    object Conflict: DbResult<Nothing>()
    data class Success<out T: Any>(val data: T): DbResult<T>()
}
