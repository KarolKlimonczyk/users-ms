package com.jvmfy.usersmicroservice.user

import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository, val bCryptPasswordEncoder: BCryptPasswordEncoder) : UserDetailsService {

    fun createNewUser(userDto: UserDto): UserDetails {
        val user = this.userRepository.save(User(firstName = userDto.firstName, lastName = userDto.lastName, password = bCryptPasswordEncoder.encode(userDto.password), email = userDto.email))
        return UserDetails(user.id.toString(), user.firstName, user.lastName, user.email)
    }

    override fun loadUserByUsername(email: String): org.springframework.security.core.userdetails.UserDetails {
        val user: User = this.userRepository.findUserByEmail(email)
                ?: throw UsernameNotFoundException("User with $email not found")
        return org.springframework.security.core.userdetails.User(user.email, user.password, true, true, true, true, emptyList())
    }
}

