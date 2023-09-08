package uz.pdp.codingbatapi.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String resource, Long id) {
        super(resource + " not found with id = " + id);
    }
}
