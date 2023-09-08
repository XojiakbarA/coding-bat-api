package uz.pdp.codingbatapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import uz.pdp.codingbatapi.marker.OnCreate;

@Data
public class SolutionDTO {
    @NotNull(message = "userId is required", groups = OnCreate.class)
    @Positive(message = "userId must be a positive")
    private Long userId;

    @NotNull(message = "problemId is required", groups = OnCreate.class)
    @Positive(message = "problemId must be a positive")
    private Long problemId;

    @NotBlank(message = "content is required", groups = OnCreate.class)
    @Size(min = 10, max = 1000, message = "content must not be less than 10 and not more than 255")
    private String content;
}
