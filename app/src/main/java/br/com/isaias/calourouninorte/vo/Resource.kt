package br.com.isaias.calourouninorte.vo

import br.com.isaias.calourouninorte.vo.Status.*

data class Resource<out T>(
    val status: Status,
    val data: T? = null,
    val message: String = "",
    val exception: Exception? = null
) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(SUCCESS, data)
        }

        fun <T> error(exception: Exception): Resource<T> {
            return Resource(ERROR, exception = exception)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(LOADING, data)
        }
    }
}