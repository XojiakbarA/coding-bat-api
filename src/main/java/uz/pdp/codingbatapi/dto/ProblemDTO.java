package uz.pdp.codingbatapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import uz.pdp.codingbatapi.marker.OnCreate;

@Data
public class ProblemDTO {
    @NotBlank(message = "name is required", groups = OnCreate.class)
    @Size(min = 3, max = 255, message = "name must not be less than 3 and not more than 255")
    private String name;

    @NotBlank(message = "instruction is required", groups = OnCreate.class)
    @Size(min = 10, max = 1000, message = "instruction must not be less than 10 and not more than 255")
    private String instruction;

    @NotBlank(message = "correctSolution is required", groups = OnCreate.class)
    @Size(min = 10, max = 1000, message = "correctSolution must not be less than 10 and not more than 255")
    private String correctSolution;

    @NotNull(message = "categoryId is required", groups = OnCreate.class)
    @Positive(message = "categoryId must be a positive")
    private Long categoryId;
}
