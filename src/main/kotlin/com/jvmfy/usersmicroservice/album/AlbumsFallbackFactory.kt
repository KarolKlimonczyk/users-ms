package com.jvmfy.usersmicroservice.album

import feign.hystrix.FallbackFactory
import org.springframework.stereotype.Component

@Component
class AlbumsFallbackFactory: FallbackFactory<AlbumsServiceClient> {

    override fun create(cause: Throwable): AlbumsServiceClient = AlbumsServiceClientFallback(cause)
}