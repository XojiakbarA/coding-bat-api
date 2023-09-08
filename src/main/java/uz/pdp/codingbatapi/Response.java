package uz.pdp.codingbatapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Response {
    private Object data;
    private String message;
    private Map<String, String> errors;

    public Response(String message) {
        this.message = message;
    }

    public Response(Object data, String message) {
        this.data = data;
        this.message = message;
    }

    public Response(Map<String, String> errors, String message) {
        this.message = message;
        this.errors = errors;
    }
}
