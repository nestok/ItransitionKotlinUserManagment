package com.funproject.developer.funproject.model

import javax.persistence.*

@Entity
@Table(name="person")
data class User(
    private var username: String = "",

    private var password: String = "",

    private var email: String = "",

    @Enumerated(EnumType.STRING)
    private var role: UserRole = UserRole.ROLE_USER,

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long = -1) {

    fun getId(): Long? {
        return id
    }

    fun getUsername(): String {
        return username
    }

    fun getRole(): Any {
        return role
    }

    fun getPassword(): String {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }

    fun setRole(role: UserRole) {
        this.role = role
    }

    fun setUsername(username: String) {
        this.username = username
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getEmail(): String {
        return email
    }

}