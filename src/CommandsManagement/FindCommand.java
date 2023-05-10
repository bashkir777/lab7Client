package CommandsManagement;

import Exceptions.NoSuchCommand;
import Exceptions.WrongTicketData;

import Modules.ExecuteScript;
import TicketStuff.Coordinates;
import TicketStuff.Event;
import TicketStuff.Ticket;
import TicketStuff.TicketType;
import Main.Main;

import java.io.File;
import java.util.Scanner;

public class FindCommand {
    public static Commands findCommand(String line, int userId, Scanner scanner) throws NoSuchCommand {
        String[] commandArr = line.split(" ");
        Commands command = null;
        for (CommandsEnum cmd : CommandsEnum.values()) {
            if (cmd.getName().equals(commandArr[0])) {
                switch (cmd) {
                    case UPDATE_ID:
                        Object[] args = new Object[]{commandArr[1], FindCommand.readTicket(scanner), userId};
                        command = new Commands(cmd, args);
                        break;
                    case ADD, ADD_IF_MAX:
                        args = new Object[]{FindCommand.readTicket(scanner), userId};
                        command = new Commands(cmd, args);
                        break;
                    case FILTER_BY_EVENT:
                        args = new Object[]{FindCommand.readEvent(scanner)};
                        command = new Commands(cmd, args);
                        break;
                    case FILTER_CONTAINS_NAME:
                        try {
                            args = new Object[]{commandArr[1]};
                            command = new Commands(cmd, args);
                            break;
                        } catch (IndexOutOfBoundsException e) {
                            throw new NoSuchCommand("В команду необходимо передать аргумент");
                        }
                    case REMOVE_BY_ID:
                        try {
                            args = new Object[]{commandArr[1], userId};
                            command = new Commands(cmd, args);
                            break;
                        } catch (IndexOutOfBoundsException e) {
                            throw new NoSuchCommand("В команду необходимо передать аргумент");
                        }
                    /*case EXECUTE_SCRIPT:
                        ExecuteScript.execute(commandArr[1]);
                        /*try{
                            Scanner scanner = new Scanner(new File(commandArr[1]));
                            StringBuilder sb = new StringBuilder();
                            while (scanner.hasNext()){
                                sb.append(scanner.nextLine()).append("\n");
                            }
                            args = new Object[]{sb.toString(), userId};
                            command = new Commands(cmd, args);
                            break;
                        }catch (Exception e){
                            throw new NoSuchCommand("Не удалось найти путь к файлу со скриптом");
                        }*/
                    case CLEAR:
                        args = new Object[]{userId};
                        command = new Commands(cmd, args);
                        break;
                    default:
                        command = new Commands(cmd);
                        break;
                }
            }
        }
        if (command == null) {
            throw new NoSuchCommand("Такой команды не существует");
        }
        return command;
    }

    public static Commands findCommand(String line, int userId) throws NoSuchCommand {
        String[] commandArr = line.split(" ");
        Commands command = null;
        for (CommandsEnum cmd : CommandsEnum.values()) {
            if (cmd.getName().equals(commandArr[0])) {
                switch (cmd) {
                    case UPDATE_ID:
                        Object[] args = new Object[]{commandArr[1], FindCommand.readTicket(), userId};
                        command = new Commands(cmd, args);
                        break;
                    case ADD, ADD_IF_MAX:
                        args = new Object[]{FindCommand.readTicket(), userId};
                        command = new Commands(cmd, args);
                        break;
                    case FILTER_BY_EVENT:
                        args = new Object[]{FindCommand.readEvent()};
                        command = new Commands(cmd, args);
                        break;
                    case FILTER_CONTAINS_NAME:
                        try {
                            args = new Object[]{commandArr[1]};
                            command = new Commands(cmd, args);
                            break;
                        } catch (IndexOutOfBoundsException e) {
                            throw new NoSuchCommand("В команду необходимо передать аргумент");
                        }
                    case REMOVE_BY_ID:
                        try {
                            args = new Object[]{commandArr[1], userId};
                            command = new Commands(cmd, args);
                            break;
                        } catch (IndexOutOfBoundsException e) {
                            throw new NoSuchCommand("В команду необходимо передать аргумент");
                        }
                    /*case EXECUTE_SCRIPT:
                        ExecuteScript.execute(commandArr[1]);
                        /*try{
                            Scanner scanner = new Scanner(new File(commandArr[1]));
                            StringBuilder sb = new StringBuilder();
                            while (scanner.hasNext()){
                                sb.append(scanner.nextLine()).append("\n");
                            }
                            args = new Object[]{sb.toString(), userId};
                            command = new Commands(cmd, args);
                            break;
                        }catch (Exception e){
                            throw new NoSuchCommand("Не удалось найти путь к файлу со скриптом");
                        }*/
                    case CLEAR:
                        args = new Object[]{userId};
                        command = new Commands(cmd, args);
                        break;
                    default:
                        command = new Commands(cmd);
                        break;
                }
            }
        }
        if (command == null) {
            throw new NoSuchCommand("Такой команды не существует");
        }
        return command;
    }

    public static Ticket readTicket() {
        Ticket ticket = new Ticket();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                if (ticket.getName() == null) {
                    System.out.print("Введите название билета: ");

                    ticket.setName(scanner.nextLine());
                }
                if (ticket.getCoordinates() == null) {

                    System.out.print("Введите координату x: ");

                    String cordX = scanner.nextLine();
                    int x;
                    double y;
                    try {
                        x = Integer.parseInt(cordX);
                    } catch (Exception e) {

                        System.out.println("координата x должна быть числом");
                        continue;
                    }
                    System.out.print("Введите координату y: ");
                    String cordY = scanner.nextLine();
                    try {
                        y = Double.parseDouble(cordY);
                    } catch (Exception e) {
                        System.out.println("координата y должна быть числом");
                        continue;
                    }
                    try {
                        ticket.setCoordinates(new Coordinates(x, y));
                    } catch (Exception e) {
                        throw new WrongTicketData("");
                    }

                }
                if (ticket.getPrice() == null) {
                    System.out.print("Введите цену билета: ");

                    try {
                        float price = Float.parseFloat(scanner.nextLine());
                        if (price < 0) {
                            throw new WrongTicketData("");
                        }
                        ticket.setPrice(price);
                    } catch (Exception e) {
                        throw new WrongTicketData("Цена должна быть положительным числом");
                    }

                }
                if (ticket.getType() == null) {

                    System.out.println("Выберете тип билета:");
                    System.out.println("1 - VIP");
                    System.out.println("2 - USUAL");
                    System.out.println("3 - BUDGETARY");
                    System.out.println("4 - CHEAP");
                    System.out.println("Введите цифру от 1 до 4:");

                    int choice;
                    try {
                        choice = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {
                        throw new WrongTicketData("Нужно ввести цифру от 1 до 4");
                    }
                    if (!(1 <= choice && choice <= 4)) {
                        throw new WrongTicketData("Нужно ввести цифру от 1 до 4");
                    }
                    switch (choice) {
                        case 1:
                            ticket.setType(TicketType.VIP);
                            break;
                        case 2:
                            ticket.setType(TicketType.USUAL);
                            break;
                        case 3:
                            ticket.setType(TicketType.BUDGETARY);
                            break;
                        case 4:
                            ticket.setType(TicketType.CHEAP);
                            break;
                    }
                }
                if (ticket.getEvent() == null) {

                    System.out.println("Заполните информацию о мероприятии");
                    System.out.println("Введите название мероприятия:");

                    String[] string = new String[3];
                    string[0] = scanner.nextLine();
                    if (!(string[0].length() >= 1)) {
                        throw new WrongTicketData("Неверное название мероприятия");
                    }

                    System.out.println("Введите количество билетов:");

                    try {
                        int x = Integer.parseInt(scanner.nextLine());
                        if (x < 1) {
                            throw new WrongTicketData("Количество билетов должно быть неотрицательно");
                        }
                        string[1] = Integer.toString(x);
                    } catch (Exception e) {
                        throw new WrongTicketData("Введите число");
                    }

                    System.out.println("Введите описание мероприятия, если его нет введите '-'");

                    string[2] = scanner.nextLine();
                    if (string[2].equals("-")) {
                        string[2] = " ";
                    }
                    ticket.setEvent(new Event(string[0], Integer.parseInt(string[1]), string[2]));
                }
                return ticket;
            } catch (WrongTicketData e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Ticket readTicket(Scanner scanner) {
        Ticket ticket = new Ticket();

        try {
            if (ticket.getName() == null) {
                ticket.setName(scanner.nextLine());
            }
            if (ticket.getCoordinates() == null) {
                String cordX = scanner.nextLine();
                int x = 0;
                double y;
                try {
                    x = Integer.parseInt(cordX);
                } catch (Exception e) {

                    throw new WrongTicketData("координата x должна быть числом");
                }
                String cordY = scanner.nextLine();
                try {
                    y = Double.parseDouble(cordY);
                } catch (Exception e) {
                    throw new WrongTicketData("Координата должна быть числом");
                }
                try {
                    ticket.setCoordinates(new Coordinates(x, y));
                } catch (Exception e) {
                    throw new WrongTicketData("");
                }

            }
            if (ticket.getPrice() == null) {

                try {
                    float price = Float.parseFloat(scanner.nextLine());
                    if (price < 0) {
                        throw new WrongTicketData("");
                    }
                    ticket.setPrice(price);
                } catch (Exception e) {
                    throw new WrongTicketData("Цена должна быть положительным числом");
                }

            }
            if (ticket.getType() == null) {
                int choice;
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (Exception e) {
                    throw new WrongTicketData("Нужно ввести цифру от 1 до 4");
                }
                if (!(1 <= choice && choice <= 4)) {
                    throw new WrongTicketData("Нужно ввести цифру от 1 до 4");
                }
                switch (choice) {
                    case 1:
                        ticket.setType(TicketType.VIP);
                        break;
                    case 2:
                        ticket.setType(TicketType.USUAL);
                        break;
                    case 3:
                        ticket.setType(TicketType.BUDGETARY);
                        break;
                    case 4:
                        ticket.setType(TicketType.CHEAP);
                        break;
                }
            }
            if (ticket.getEvent() == null) {
                String[] string = new String[3];
                string[0] = scanner.nextLine();
                if (!(string[0].length() >= 1)) {
                    throw new WrongTicketData("Неверное название мероприятия");
                }

                try {
                    int x = Integer.parseInt(scanner.nextLine());
                    if (x < 1) {
                        throw new WrongTicketData("Количество билетов должно быть неотрицательно");
                    }
                    string[1] = Integer.toString(x);
                } catch (Exception e) {
                    throw new WrongTicketData("Введите число");
                }
                string[2] = scanner.nextLine();
                if (string[2] == "-") {
                    string[2] = " ";
                }
                ticket.setEvent(new Event(string[0], Integer.parseInt(string[1]), string[2]));
            }

        } catch (WrongTicketData e) {
            System.out.println("В скрипте содержиться ошибка");
            System.exit(0);
        }
        return ticket;
    }


    public static Event readEvent() {
        Scanner scanner = new Scanner(System.in);
        Event event = new Event();
        while (true) {
            try {
                System.out.println("Заполните информацию о мероприятии");
                if (event.getName() == null) {
                    System.out.println("Введите название мероприятия:");
                    String name = scanner.nextLine();
                    if (!(name.length() >= 1)) {
                        throw new WrongTicketData("Неверное название мероприятия");
                    }
                    event.setName(name);
                }
                if (event.getTicketsCount() == -1) {
                    System.out.println("Введите количество билетов:");
                    try {
                        int x = Integer.parseInt(scanner.nextLine());
                        if (x < 1) {
                            throw new WrongTicketData("Количество билетов должно быть неотрицательно");
                        }
                        event.setTicketsCount(x);
                    } catch (Exception e) {
                        throw new WrongTicketData("Введите натуральное число");
                    }
                }
                if (event.getDescription() == null) {
                    System.out.println("Введите описание мероприятия, если его нет введите '-'");
                    String description = scanner.nextLine();
                    if (description.equals("-")) {
                        description = " ";
                    }
                    event.setDescription(description);
                }
                return event;

            } catch (WrongTicketData e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Event readEvent(Scanner scanner) {
        Event event = new Event();
        try {
            if (event.getName() == null) {
                String name = scanner.nextLine();
                if (!(name.length() >= 1)) {
                    throw new WrongTicketData("Неверное название мероприятия");
                }
                event.setName(name);
            }
            if (event.getTicketsCount() == -1) {
                try {
                    int x = Integer.parseInt(scanner.nextLine());
                    if (x < 1) {
                        throw new WrongTicketData("Количество билетов должно быть неотрицательно");
                    }
                    event.setTicketsCount(x);
                } catch (Exception e) {
                    throw new WrongTicketData("Введите натуральное число");
                }
            }
            if (event.getDescription() == null) {
                String description = scanner.nextLine();
                if (description.equals("-")) {
                    description = " ";
                }
                event.setDescription(description);
            }


        } catch (WrongTicketData e) {
            System.out.println("В скрипте содержиться ошибка");
            System.exit(0);
        }
        return event;
    }
}
