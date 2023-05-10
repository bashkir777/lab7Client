package Exceptions;
/**
 * Класс, описывающий ошибку, возникающую при неправильном написании какой-либо команды.
 * @author Supletsov Dmitriy "supletsovd@gmail.com"
 */
public class WrongCommandSyntax extends Exception{
    private String message;
    public WrongCommandSyntax(String message){
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
