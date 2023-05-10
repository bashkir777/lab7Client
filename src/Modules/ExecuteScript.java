package Modules;

import CommandsManagement.Commands;
import CommandsManagement.FindCommand;
import Exceptions.NoSuchCommand;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.Scanner;

public class ExecuteScript {
    public static ArrayList<Commands> execute(String filePath, int userId) throws NoSuchCommand {
        Scanner scanner = null;
        try{
            scanner = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            System.out.println("Не удалось найти файл с таким названием");
        }
        ArrayList<Commands> list = new ArrayList<>();
        while (scanner.hasNext()){
            String command = scanner.nextLine();
            Commands cmd = FindCommand.findCommand(command, userId, scanner);
            list.add(cmd);
        }
        //System.out.println(list.size());
        return list;
    }

}
