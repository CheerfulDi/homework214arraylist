package pro.sky.java.course2.exceptions;

public class StringListItemIsNullException extends RuntimeException{
    public StringListItemIsNullException() {
        super("Not null value is required!");
    }
}
