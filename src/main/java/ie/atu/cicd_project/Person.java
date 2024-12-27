package ie.atu.cicd_project;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Person {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Employee ID invalid")
    @Size(max = 7, message = "ID cannot be more than 7 characters")
    private String id;

    @Email(message = "Invalid email address")
    private String email;

    @Min(value = 0, message = "Age must be greater than or equal to 0")
    private int age;

    @NotBlank(message = "Position cannot be blank")
    private String position;

    @NotBlank(message = "Department cannot be blank")
    private String department;

}