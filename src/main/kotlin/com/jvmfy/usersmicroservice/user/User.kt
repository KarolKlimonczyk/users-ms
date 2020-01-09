package com.jvmfy.usersmicroservice.user

import java.util.*
import javax.persistence.*

@Table(name = "USERS")
@Entity
data class User(@Id
           @GeneratedValue(strategy = GenerationType.AUTO)
           val id: UUID ? = null,
           @Column(name = "USR_FIRST_NAME")
           val firstName: String,
           @Column(name = "USR_LAST_NAME")
           val lastName: String,
           @Column(name = "USR_PASSWORD")
           val password: String,
           @Column(name = "USR_EMAIL")
           val email: String)