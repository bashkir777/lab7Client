package TicketStuff;

import Exceptions.WrongTicketData;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private int x; //Максимальное значение поля: 121
    private Double y; //Поле не может быть null
    public Coordinates(int x, Double y) throws WrongTicketData {
        if (x > 121){
            throw new WrongTicketData("Максимальное значение поля х в классе Coordinates 121");
        }
        if (y == null){
            throw new WrongTicketData("Поле у в классе Coordinates не может принимать значение null");
        }
        this.x= x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

}