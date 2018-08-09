package com.funproject.developer.funproject.model

import org.hibernate.annotations.Table
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity
class Person(
        val username: String,
        val password: String,
        val role: UserRole,
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = -1) {

    private constructor() : this("", "", UserRole.ROLE_USER)
}