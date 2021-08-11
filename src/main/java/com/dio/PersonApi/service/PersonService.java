package com.dio.PersonApi.service;

import com.dio.PersonApi.Entity.Person;
import com.dio.PersonApi.exception.PersonNotFoundException;
import com.dio.PersonApi.dto.request.PersonDTO;
import com.dio.PersonApi.dto.response.MessageResponseDTO;
import com.dio.PersonApi.mapper.PersonMapper;
import com.dio.PersonApi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {
    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person person = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(person);

        return createMessageResponse(savedPerson.getId(), "Created person with ID ");
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);

        return personMapper.toDTO( person );
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);

        Person person = personMapper.toModel(personDTO);
        Person updatedPerson = personRepository.save(person);

        return createMessageResponse(updatedPerson.getId(), "Updated person with ID ");
    }

    private Person verifyIfExists( Long id ) throws PersonNotFoundException{
        return personRepository.findById(id)
                .orElseThrow( () -> new PersonNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String msg) {
        return MessageResponseDTO
                .builder()
                .message(msg + id)
                .build();
    }
}
