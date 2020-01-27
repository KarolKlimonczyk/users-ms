package com.jvmfy.usersmicroservice.user

import com.jvmfy.usersmicroservice.album.AlbumsServiceClient
import org.springframework.core.env.Environment
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(val userRepository: UserRepository, val bCryptPasswordEncoder: BCryptPasswordEncoder, val albumsServiceClient: AlbumsServiceClient, val env: Environment) : UserDetailsService {

    fun createNewUser(userDto: UserDto): UserDetails {
        val user = this.userRepository.save(User(firstName = userDto.firstName, lastName = userDto.lastName, password = bCryptPasswordEncoder.encode(userDto.password), email = userDto.email))
        return UserDetails(user.id.toString(), user.firstName, user.lastName, user.email, emptyList())
    }

    fun findUserByEmail(email: String) = this.userRepository.findUserByEmail(email)

    fun getUserByUserId(userId: String): UserDetails {
        val user = this.userRepository.findById(UUID.fromString(userId)).orElseThrow { throw NoSuchElementException("User not found") }
        val albums = this.albumsServiceClient.getAlbumsForUser(userId)
        return UserDetails(user.id.toString(), user.firstName, user.lastName, user.email, albums)
    }

    override fun loadUserByUsername(email: String): org.springframework.security.core.userdetails.UserDetails {
        val user: User = this.userRepository.findUserByEmail(email)
                ?: throw UsernameNotFoundException("User with $email not found")
        return org.springframework.security.core.userdetails.User(user.email, user.password, true, true, true, true, emptyList())
    }
}
