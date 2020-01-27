package com.jvmfy.usersmicroservice.album

import feign.Response
import feign.codec.ErrorDecoder
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

@Component
class FeignErrorDecoder : ErrorDecoder {

    override fun decode(methodKey: String, response: Response): Exception = when (response.status()) {
        403 -> throw ResponseStatusException(HttpStatus.valueOf(response.status()), "Access forbidden")
        404 -> throw ResponseStatusException(HttpStatus.valueOf(response.status()), "Service not found")
        else -> throw Exception(response.reason())
    }
}