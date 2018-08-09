package com.funproject.developer.funproject.controller

import com.funproject.developer.funproject.model.Person
import com.funproject.developer.funproject.model.UserRole
import com.funproject.developer.funproject.repository.PersonRepository
import com.funproject.developer.funproject.service.UserService
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin

@RestController
@CrossOrigin(origins = arrayOf("http://localhost:4200"), maxAge = 3600)
class UserController {

    @Autowired
    lateinit var repository: PersonRepository

    @RequestMapping("/save")
    fun save(): String {
        repository.save(Person("Jack", "Smith", UserRole.ROLE_USER))
        repository.save(Person("Adam", "Johnson", UserRole.ROLE_USER))
        repository.save(Person("Kim", "Smith", UserRole.ROLE_USER))
        repository.save(Person("David", "Williams", UserRole.ROLE_USER))
        repository.save(Person("Peter", "Davis", UserRole.ROLE_USER))

        return "Done"
    }

//    private val userService: UserService
//
//
//    @RequestMapping("/save")
//    fun save(): String {
//        return userService.saveUsers()
//    }

    @RequestMapping("/users")
    fun findAllUsers() = repository.findAll()


    @RequestMapping("/findall")
    fun findAll() = repository.findAll()

    @RequestMapping("/findbyid/{id}")
    fun findById(@PathVariable id: Long)
            = repository.findById(id)

    @RequestMapping("findbylastname/{lastName}")
    fun findByLastName(@PathVariable lastName: String)
            = repository.findByUsername(lastName)

}