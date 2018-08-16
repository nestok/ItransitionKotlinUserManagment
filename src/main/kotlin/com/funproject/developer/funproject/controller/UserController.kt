package com.funproject.developer.funproject.controller

import com.funproject.developer.funproject.dto.userDto.ContributorsListDto
import com.funproject.developer.funproject.dto.userDto.UserAddDto
import com.funproject.developer.funproject.dto.userDto.UserListDto
import com.funproject.developer.funproject.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = arrayOf("http://localhost:4200"), maxAge = 3600)
@RequestMapping(value = "/user")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/loadAll")
    fun findAllUsers(): ArrayList<UserListDto> {
        return userService.findAllUsers()
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    fun register(@RequestBody user: UserAddDto) {
        userService.register(user)
    }

    @GetMapping("/loadAllContributors")
    @ResponseStatus(HttpStatus.OK)
    fun findAllContributors(): ArrayList<ContributorsListDto> {
        return userService.findAllContributors()
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    fun deleteUser(@PathVariable(value = "id") id: Long) {
        userService.deleteUser(id)
    }

//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Cannot operate function")  // 404
//    inner class ErrorRegisterUserException : RuntimeException()

}