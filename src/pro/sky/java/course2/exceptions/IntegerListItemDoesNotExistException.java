package pro.sky.java.course2.exceptions;

public class IntegerListItemDoesNotExistException extends RuntimeException {

    public IntegerListItemDoesNotExistException() {
        super("This item does not exist in the list.");
    }
}
