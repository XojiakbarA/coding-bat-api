package uz.pdp.codingbatapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import uz.pdp.codingbatapi.marker.OnCreate;

@Data
public class UserDTO {
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "email is not valid")
    @NotBlank(message = "email is required", groups = OnCreate.class)
    private String email;

    @NotBlank(message = "password is required", groups = OnCreate.class)
    @Size(min = 3, max = 255, message = "password must not be less than 3 and not more than 255")
    private String password;
}
