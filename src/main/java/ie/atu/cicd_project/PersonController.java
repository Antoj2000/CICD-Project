package ie.atu.cicd_project;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
@Validated
public class PersonController {

    private PersonService myService;

    public PersonController(PersonService myService) {

        this.myService = myService;
    }

    private List<Person> list = new ArrayList<>();
    //Creating a class which is interested in request and responses. Separation of concern
    @GetMapping
    public List<Person> getAllPersons(){

        return myService.getAllPersons();
    }
    @PutMapping("/{id}")
    List<Person> updatePerson(@PathVariable String id , @RequestBody @Valid Person updatedProduct){

        return myService.updatePersons(id, updatedProduct);
    }

    @DeleteMapping("/{id}")
    List<Person> deletePerson(@PathVariable String id ){

        return myService.deletePerson(id);
    }




}
