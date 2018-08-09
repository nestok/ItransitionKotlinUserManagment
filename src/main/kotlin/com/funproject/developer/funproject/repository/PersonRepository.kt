package com.funproject.developer.funproject.repository
 
import com.funproject.developer.funproject.model.Person
import org.springframework.stereotype.Repository
import org.springframework.data.repository.CrudRepository


@Repository
interface PersonRepository : CrudRepository<Person, Long> {
 
	fun findByUsername(username: String): Iterable<Person>
}