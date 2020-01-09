package com.jvmfy.usersmicroservice.user

import org.springframework.core.env.Environment
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/users")
class UserResource(val userService: UserService, val environment: Environment) {

    @GetMapping("/status")
    fun status(): ResponseEntity<String>? = environment.getProperty("local.server.port")?.let { ResponseEntity.ok(it) }

    @PostMapping("/create")
    fun createUser(@Valid @RequestBody userDto: UserDto): ResponseEntity<UserDetails> = ResponseEntity.ok(this.userService.createNewUser(userDto))
}