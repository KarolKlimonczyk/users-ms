package com.jvmfy.usersmicroservice.user

import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class UserDto(@NotNull @Size(min = 2, message = "Min first name length is 2") val firstName: String,
                   @NotNull @Size(min = 2, message = "Min last name length is 2") val lastName: String,
                   @NotNull @Size(min = 8, max = 36, message = "Password must be equal or greater than 8 characters and less than 36") val password: String,
                   @NotNull @Email val email: String)