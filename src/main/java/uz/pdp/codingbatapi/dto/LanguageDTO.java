package uz.pdp.codingbatapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LanguageDTO {
    @NotBlank(message = "name is required")
    @Size(min = 3, max = 255, message = "name must not be less than 3 and not more than 255")
    private String name;
}
