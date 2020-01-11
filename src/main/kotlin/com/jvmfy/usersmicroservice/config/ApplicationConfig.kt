package com.jvmfy.usersmicroservice.config

import com.jvmfy.usersmicroservice.user.UserService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class ApplicationConfig(@Value("\${gateway.ip}") val gatewayIP: String, val userService: UserService, val passwordEncoder: BCryptPasswordEncoder) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
        http.authorizeRequests().antMatchers("/**").hasIpAddress(gatewayIP)
                .and().addFilter(this.getAuthenticationFilter())
        http.headers().frameOptions().disable() // dirty, no production solution to fix h2console
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userService).passwordEncoder(this.passwordEncoder)
    }

    private fun getAuthenticationFilter() = AuthenticationFilter().also {
        it.setAuthenticationManager(authenticationManager())
    }
}