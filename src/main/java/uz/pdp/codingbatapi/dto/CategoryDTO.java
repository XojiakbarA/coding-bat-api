package uz.pdp.codingbatapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import uz.pdp.codingbatapi.marker.OnCreate;

@Data
public class CategoryDTO {
    @NotBlank(message = "name is required", groups = OnCreate.class)
    @Size(min = 3, max = 255, message = "name must not be less than 3 and not more than 255")
    private String name;

    @NotBlank(message = "description is required", groups = OnCreate.class)
    @Size(min = 10, max = 255, message = "description must not be less than 10 and not more than 255")
    private String description;

    @NotNull(message = "languageId is required", groups = OnCreate.class)
    @Positive(message = "languageId must be a positive")
    private Long languageId;
}
