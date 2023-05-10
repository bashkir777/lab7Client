package Exceptions;

public class NoSuchCommand extends Exception{
    public NoSuchCommand(String message){
        super(message);
    }
}
