package com.jvmfy.usersmicroservice.user

import com.jvmfy.usersmicroservice.album.Album

data class UserDetails(val userUUID: String,
                       val firstName: String,
                       val lastName: String,
                       val email: String,
                       val albums: List<Album>
)