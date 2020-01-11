package com.jvmfy.usersmicroservice.user

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findUserByEmail(email: String): User?
}