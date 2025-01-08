package ie.atu.cicd_project;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Optional;


import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }
    public Person addPerson(Person person)
    {

        return  personRepository.save(person);
    }

    public List<Person> getAllPersons() {

        return personRepository.findAll();
    }

    // Update person by employeeId (Update)
    @Transactional
    public Person updatePerson(String employeeId, Person updatedPerson) {
        // Check if the person with the given employeeId exists
        Optional<Person> existingPersonOptional = personRepository.findById(updatedPerson.getId());

        if (existingPersonOptional.isPresent()) {
            Person existingPerson = existingPersonOptional.get();
            // Update the fields of the existing person with the new values
            existingPerson.setName(updatedPerson.getName());
            existingPerson.setEmployeeId(updatedPerson.getEmployeeId());
            existingPerson.setEmail(updatedPerson.getEmail());
            existingPerson.setAge(updatedPerson.getAge());
            existingPerson.setPosition(updatedPerson.getPosition());
            existingPerson.setDepartment(updatedPerson.getDepartment());
            // Save the updated person back to the database
            return personRepository.save(existingPerson);
        } else {
            throw new IllegalArgumentException("Person with Employee ID " + employeeId + " not found");
        }
    }
    public void deletePerson(String employeeId) {
        // Find person by employeeId (assuming employeeId is unique)
        Optional<Person> personOptional = personRepository.findAll().stream()
                .filter(person -> person.getEmployeeId().equals(employeeId))
                .findFirst();

        if (personOptional.isPresent()) {
            // Delete the person
            personRepository.delete(personOptional.get());
        } else {
            throw new IllegalArgumentException("Person with Employee ID " + employeeId + " not found");
        }
    }

}

