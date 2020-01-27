package com.jvmfy.usersmicroservice.album

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "albums-ms")
interface AlbumsServiceClient {

    @GetMapping("/users/{usersId}/albums")
    fun getAlbumsForUser(@PathVariable usersId: String): List<Album>
}