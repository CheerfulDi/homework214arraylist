package pro.sky.java.course2.exceptions;

public class IntegerListItemIsNullException extends RuntimeException{

    public IntegerListItemIsNullException() {
        super("Not null value is required!");
    }
}
