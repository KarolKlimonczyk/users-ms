package com.jvmfy.usersmicroservice.user

data class UserDetails(val userUUID: String,
                       val firstName: String,
                       val lastName: String,
                       val email: String)