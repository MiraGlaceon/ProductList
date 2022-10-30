package mira.space.exception;

public class ListNotFoundException extends RuntimeException{
    public ListNotFoundException(Long id) {
        super("List not found. ID: " + id);
    }
}
