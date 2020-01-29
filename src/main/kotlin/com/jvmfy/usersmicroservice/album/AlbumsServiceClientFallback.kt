package com.jvmfy.usersmicroservice.album

import feign.FeignException
import org.slf4j.LoggerFactory

class AlbumsServiceClientFallback(private val cause: Throwable) : AlbumsServiceClient {

    private val logger = logger()

    override fun getAlbumsForUser(userId: String): List<Album> {
        if (this.cause is FeignException) {
            logger.error("Cannot obtain albums-ms. Error message: ${cause.localizedMessage}")
        } else {
            logger.error("An unexpected error occurs. Cannot obtain albums-ms. Cause: ${cause.localizedMessage}")
        }

        return emptyList()
    }
}

inline fun <reified T> T.logger(): org.slf4j.Logger {
    return LoggerFactory.getLogger(T::class.java)
}