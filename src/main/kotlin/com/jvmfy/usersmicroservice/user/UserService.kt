package com.jvmfy.usersmicroservice.user

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository, val bCryptPasswordEncoder: BCryptPasswordEncoder) {

    fun createNewUser(userDto: UserDto): UserDetails {
        val user = this.userRepository.save(User(firstName = userDto.firstName, lastName = userDto.lastName, password = bCryptPasswordEncoder.encode(userDto.password), email = userDto.email))
        return UserDetails(user.id.toString(), user.firstName, user.lastName, user.email)
    }
}

