package ie.atu.cicd_project;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/employee")  //Base URL Mapping
@Validated
public class PersonController {

    private final PersonService myService;  //Dependency Injection for service

    public PersonController(PersonService myService) {

        this.myService = myService;
    }

    private List<Person> list = new ArrayList<>();
    //Creating a class which is interested in request and responses. Separation of concern

    @PostMapping
    public ResponseEntity<?> addPerson(@RequestBody @Valid Person person, BindingResult result ){
        if (result.hasErrors()){
            List<ErrorDetails> errors = new ArrayList<>();
            result.getFieldErrors().forEach(error -> {
                String fieldName = error.getField();
                String errorMessage = error.getDefaultMessage();
                errors.add(new ErrorDetails(fieldName, errorMessage));
            });
            return ResponseEntity.badRequest().body(errors);
        }
        //Delegates logic to service layer
        Person savedPerson = myService.addPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
    }


    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons(){
        List<Person> persons = myService.getAllPersons();
        return ResponseEntity.ok(persons);  // Returns 200 OK with list of persons
    }
    @PutMapping("/{employeeId}")
    public ResponseEntity<?>updatePerson(@PathVariable String employeeId, @RequestBody @Valid Person updatedEmployee, BindingResult result){
        if (result.hasErrors()) {
            List<ErrorDetails>errors = new ArrayList<>();
            result.getFieldErrors().forEach(error -> {
                String fieldName = error.getField();
                String errorMessage = error.getDefaultMessage();
                errors.add(new ErrorDetails(fieldName, errorMessage));
            });
            return ResponseEntity.badRequest().body(errors);
        }

        Person updatedPerson = myService.updatePerson(employeeId, updatedEmployee);
        if (updatedPerson == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found with Employee ID: " + employeeId);
        }
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<?> deletePerson(@PathVariable String employeeId){
        try {
            myService.deletePerson(employeeId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Employee deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with Employee ID " + employeeId + " not found");
        }
    }

}
