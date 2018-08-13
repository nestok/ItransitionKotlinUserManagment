package com.funproject.developer.funproject.security.model

import com.google.common.collect.ImmutableSet
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import java.io.Serializable
import java.util.*

class JwtAuthenticationToken : Authentication {

    private val userDetails: JwtUserDetails?

    private val credentials: Serializable?
    private val grantedAuthorities: Collection<GrantedAuthority>?
    private var isAuthenticated: Boolean = false

    constructor(token: String) {
        this.credentials = token
        this.userDetails = null
        this.grantedAuthorities = null
    }

    constructor(userDetails: JwtUserDetails) {
        this.credentials = null
        this.userDetails = userDetails
        this.grantedAuthorities = ImmutableSet.copyOf(userDetails.getAuthorities())
        this.isAuthenticated = true
    }

    override fun getName(): String? {
        return if (Objects.isNull(this.userDetails)) null else this.userDetails!!.getUsername()
    }

    override fun getAuthorities(): Collection<GrantedAuthority>? {
        return this.grantedAuthorities
    }

    override fun getCredentials(): Serializable? {
        return this.credentials
    }

    override fun getDetails(): Any? {
        return this.userDetails
    }

    override fun getPrincipal(): Any? {
        return this.userDetails
    }

    override fun isAuthenticated(): Boolean {
        return this.isAuthenticated
    }

    override fun setAuthenticated(isAuthenticated: Boolean) {
        if (isAuthenticated) {
            throw IllegalArgumentException("Once created you cannot set this token to authenticated.")
        }
        this.isAuthenticated = false
    }
}
