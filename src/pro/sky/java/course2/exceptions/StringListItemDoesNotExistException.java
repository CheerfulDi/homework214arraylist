package pro.sky.java.course2.exceptions;

public class StringListItemDoesNotExistException extends RuntimeException{

    public StringListItemDoesNotExistException() {
        super("This item does not exist in the list.");
    }
}
