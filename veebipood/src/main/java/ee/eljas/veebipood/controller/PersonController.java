package ee.eljas.veebipood.controller;

import ee.eljas.veebipood.dto.PersonLoginRecordDto;
import ee.eljas.veebipood.entity.Person;
import ee.eljas.veebipood.repository.PersonRepository;
import ee.eljas.veebipood.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @GetMapping("persons")
    public List<Person> getPersons(){
        return personRepository.findAll();
    }

    @DeleteMapping("persons/{id}")
    public List<Person> deletePerson(@PathVariable Long id){
        personRepository.deleteById(id); //Kustutan
        return personRepository.findAll(); //Uuenenud seis
    }

    @PostMapping("signup")
    public Person signup(@RequestBody Person person){
        personService.validate(person);
        return personRepository.save(person); //Siin salvestab
    }

    @PostMapping("login")
    public Person login(@RequestBody PersonLoginRecordDto personDto){
        Person dbPerson = personRepository.findByEmail(personDto.email());
        if(dbPerson==null){
            throw new RuntimeException("Invalid email");
        }
        if(!dbPerson.getPassword().equals(personDto.password())) {
            throw new RuntimeException("Invalid password");
        }
        return dbPerson;

    }
}
