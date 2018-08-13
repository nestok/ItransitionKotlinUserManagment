package com.funproject.developer.funproject.security.model

import com.funproject.developer.funproject.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.HashSet

class JwtUserDetails(user: User) : UserDetails {

    val id: Long?
    private val username: String
    private val password: String
    private val authorities: MutableSet<GrantedAuthority>

    init {
        this.id = user.id
        this.username = user.username
        this.password = user.password
        this.authorities = HashSet()
        this.authorities.add(SimpleGrantedAuthority(user.role.toString()))
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return this.authorities
    }

    override fun getPassword(): String {
        return this.password
    }

    override fun getUsername(): String {
        return this.username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    fun getId(): Long {
        return id!!
    }
}