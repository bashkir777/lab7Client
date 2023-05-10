package Exceptions;
/**
 * Класс, описывающий ошибку, возникающую при вводе несуществующего адреса скрипта.
 * @author Supletsov Dmitriy "supletsovd@gmail.com"
 */
public class ScriptNotFound extends Exception{
    private String message;
    public ScriptNotFound(String message){
        this.message = message;
    }
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
