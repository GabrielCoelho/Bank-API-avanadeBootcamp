package br.com.devcoelho.apirest.bank.repository;

import br.com.devcoelho.apirest.bank.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {}
