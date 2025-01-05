package ie.atu.cicd_project;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ErrorDetails {
    private String errorStatus;
    private String errorMessage;
}
