package com.jvmfy.usersmicroservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class UsersMicroserviceApplication

fun main(args: Array<String>) {
    runApplication<UsersMicroserviceApplication>(*args)
}
