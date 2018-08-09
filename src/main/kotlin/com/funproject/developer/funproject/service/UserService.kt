package com.funproject.developer.funproject.service

import com.funproject.developer.funproject.model.Person
import com.funproject.developer.funproject.model.UserRole
import com.funproject.developer.funproject.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
public class UserService {

    @Autowired
    lateinit var repository: PersonRepository

    fun saveUsers() : String {
        repository.save(Person("Jack", "Smith", UserRole.ROLE_USER))
        repository.save(Person("Adam", "Johnson", UserRole.ROLE_USER))
        repository.save(Person("Kim", "Smith", UserRole.ROLE_USER))
        repository.save(Person("David", "Williams", UserRole.ROLE_USER))
        repository.save(Person("Peter", "Davis", UserRole.ROLE_USER))
        return "Done"
    }

}