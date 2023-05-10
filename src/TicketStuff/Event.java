package TicketStuff;

import Exceptions.WrongTicketData;

import java.io.Serializable;

public class Event implements Serializable {
    private static int idCounter = 1;
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name = null; //Поле не может быть null, Строка не может быть пустой
    private long ticketsCount = -1; //Значение поля должно быть больше 0
    private String description = null; //Поле может быть null
    public Event(){
        this.id = idCounter;
        idCounter ++;
    }
    public Event(String name, long ticketsCount, String description)  throws WrongTicketData {
        if (name == null || name.equals("")){
            throw new WrongTicketData("Некорректное значение поля name у класса Event");
        }
        if (ticketsCount <= 0){
            throw new WrongTicketData("Значение поля ticketsCount у класса Event должно быть больше нуля");
        }
        this.name = name;
        if (description != null){
            this.description = description;
        }
        this.ticketsCount = ticketsCount;
        this.id = idCounter;
        idCounter ++;
    }

    @Override
    public String toString() {
        if (description != null){
            return name + description;
        }
        return name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getTicketsCount() {
        return ticketsCount;
    }

    public String getDescription() {
        return description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTicketsCount(long ticketsCount) {
        this.ticketsCount = ticketsCount;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
