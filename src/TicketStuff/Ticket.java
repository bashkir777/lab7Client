package TicketStuff;

import Exceptions.WrongTicketData;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class Ticket implements Serializable, Comparable<Ticket> {
    private static final long serialVersionUID = -4792199269162928031L;
    private Long id = null;
    private String name = null;
    private Coordinates coordinates = null;
    private java.time.ZonedDateTime creationDate;
    private Float price = null;
    private TicketType type = null;
    private Event event = null;
    public Ticket() {
        this.creationDate = java.time.ZonedDateTime.now();
    }




    @Override
    public int compareTo(Ticket ticket) {
        if (this.price < ticket.price){
            return -1;
        } else if (this.price == ticket.price) {
            return 0;
        }else{
            return 1;
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) throws WrongTicketData {
        if (name == null || name.equals("")){
            throw new WrongTicketData("Некорректное значение поля name у класса Ticket");
        }
        this.name = name;
    }

    public void setPrice(Float price) throws WrongTicketData {
        if (price == null || price <= 0){
            throw new WrongTicketData("Поле price у класса Ticket не может принимать неположительное значение");
        }
        this.price = price;
    }

    public void setType(TicketType type) throws WrongTicketData{
        if(type == null){
            throw new WrongTicketData("Поле type у класса Ticket не может принимать значение null");
        }
        this.type = type;
    }

    public void setCoordinates(Coordinates coordinates) throws WrongTicketData{
        if(coordinates == null){
            throw new WrongTicketData("Поле coordinates у класса Ticket не может принимать значение null");
        }
        this.coordinates = coordinates;
    }

    public void setEvent(Event event) throws WrongTicketData{
        if(event == null){
            throw new WrongTicketData("Поле event у класса Ticket не может принимать значение null");
        }
        this.event = event;
    }

    @Override
    public String toString() {
        return "Название: "+this.name+" Цена: "+this.price+" Событие: "+ this.event.toString();
    }

    public Long getId() {
        return id;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Float getPrice() {
        return price;
    }

    public TicketType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Event getEvent() {
        return event;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }
}
