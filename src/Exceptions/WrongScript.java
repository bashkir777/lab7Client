package Exceptions;

/**
 * Исключение возникающее при неправильном содержании файла со скриптом
 * @author Supletsov Dmitriy "supletsovd@gmail.com"
 */
public class WrongScript extends Exception{
    public WrongScript(){
        super("Неправильное содержание файла со скриптом");
    }

}
