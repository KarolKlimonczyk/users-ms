package com.jvmfy.usersmicroservice.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.jvmfy.usersmicroservice.user.LoginRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter : UsernamePasswordAuthenticationFilter() {

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

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?, authResult: Authentication?) {
        super.successfulAuthentication(request, response, chain, authResult)

        //todo genereate jwt
    }
}