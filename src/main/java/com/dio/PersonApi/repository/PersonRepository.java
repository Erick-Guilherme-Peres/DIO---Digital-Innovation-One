package com.dio.PersonApi.repository;

import com.dio.PersonApi.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
