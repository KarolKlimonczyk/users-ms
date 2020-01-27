package com.jvmfy.usersmicroservice.user

import org.springframework.core.env.Environment
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/users")
class UserResource(val userService: UserService, val environment: Environment) {

    @GetMapping("/status")
    fun status(): ResponseEntity<String>? = ResponseEntity.ok("Port: ${environment.getProperty("local.server.port")}. Jwt secret: ${environment.getProperty("app.jwt.secret")}")

    @PostMapping("/create")
    fun createUser(@Valid @RequestBody userDto: UserDto): ResponseEntity<UserDetails> = ResponseEntity.ok(this.userService.createNewUser(userDto))

    @GetMapping("/{userId}")
    fun getUser(@PathVariable userId: String): ResponseEntity<UserDetails> {
        return try {
            ResponseEntity.ok(this.userService.getUserByUserId(userId))
        } catch (e: NoSuchElementException) {
            ResponseEntity.noContent().build();
        }
    }
}