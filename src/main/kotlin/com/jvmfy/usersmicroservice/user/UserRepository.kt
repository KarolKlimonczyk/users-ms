package com.jvmfy.usersmicroservice.user

import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository : CrudRepository<User, UUID> {
    fun findUserByEmail(email: String): User?
}