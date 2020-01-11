package com.jvmfy.usersmicroservice.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.jvmfy.usersmicroservice.user.LoginRequest
import com.jvmfy.usersmicroservice.user.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.core.env.Environment
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.lang.IllegalStateException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter(private val userService: UserService, private val env: Environment) : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val objectMapper = ObjectMapper().registerModule(KotlinModule())
        val credentials = objectMapper.readValue<LoginRequest>(request.inputStream)

        return this.authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                        credentials.email,
                        credentials.password,
                        emptyList()
                )
        )
    }

    override fun successfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain, authResult: Authentication) {
        val userName = (authResult.principal as User).username
        val user = this.userService.findUserByEmail(userName)

        val token = Jwts.builder()
                .setSubject(user?.id.toString())
                .setExpiration(Date(System.currentTimeMillis() + (this.env.getProperty("app.jwt.expiration-time")?.toLong() ?: throw IllegalStateException("Cannot find JWT expiration time property"))))
                .signWith(SignatureAlgorithm.HS512, env.getProperty("app.jwt.secret"))
                .compact()

        response.addHeader("token", token)
        response.addHeader("userId", user!!.id.toString())
    }
}