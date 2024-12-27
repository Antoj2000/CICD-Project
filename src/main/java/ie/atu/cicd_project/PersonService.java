package ie.atu.cicd_project;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
@Service
public class PersonService {

    private  List<Person> myList = new ArrayList<>();
    public List<Person> addProduct(@Valid Person person)
    {

        //Do business stuff like retrieving details from db, or generating files pdfs
        myList.add(person);
        return myList;
    }

    public List<Person> getAllPersons() {

        return myList;
    }

    public List<Person> updatePersons(@PathVariable String id, @Valid @RequestBody Person updatedPerson) {
        for (Person person : myList) { // runs through each entry into array
            if (person.getId().equals(id)) {   //checks id
                person.setName(updatedPerson.getName());  //updates each detail of product

                break;
            }
        }
        return myList;
    }

    public List<Person> deletePerson(@PathVariable String id) {
        for (Person person : myList) { // runs through each entry into array
            if (person.getId().equals(id)) {
                myList.remove(person);
                break;
            }
        }
        return myList;
    }

}

