package com.jvmfy.usersmicroservice.album

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "albums-ms", fallbackFactory = AlbumsFallbackFactory::class)
interface AlbumsServiceClient {

    @GetMapping("/users/{userId}/albums")
    fun getAlbumsForUser(@PathVariable userId: String): List<Album>
}