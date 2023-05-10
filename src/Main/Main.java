package Main;

import CommandsManagement.Commands;
import CommandsManagement.CommandsEnum;
import CommandsManagement.FindCommand;
import Exceptions.NoSuchCommand;
import Modules.ExecuteScript;
import Modules.Receive;
import Modules.Send;

import java.io.IOException;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean logged = false;
        int userId = 0;
        try{
            DatagramChannel dc = DatagramChannel.open();
            dc.configureBlocking(true);
            InetAddress host = InetAddress.getByName("localhost");
            int port = 0;
            try{
                port = Integer.parseInt(args[0]);
            }catch (Exception e){
                System.out.println("В качестве аргумента в программу должен быть подан порт");
            }
            SocketAddress addr = new InetSocketAddress(host, port);
            dc.connect(addr);
            while (true){
                if (!logged){
                    System.out.println("Введите: +, если у вас есть аккаунт, или что-нибудь другое, если его нет");
                    String str = scanner.nextLine();
                    boolean haveAcc = str.equals("+") ? true : false;
                    String login;
                    String password;
                    if (haveAcc){
                        System.out.println("Введите логин:");
                        login = scanner.nextLine();
                        System.out.println("Введите пароль:");
                        password = scanner.nextLine();
                    }else{
                        System.out.println("Придумате логин(не более 20 символов):");
                        login = scanner.nextLine();
                        System.out.println("Придумайте пароль(не менее 5 символов):");
                        password = scanner.nextLine();
                        if(password.length()<5){
                            System.out.println("Пароль должен содержать 5 или более символов");
                            continue;
                        }
                    }

                    MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
                    byte[] byteArr = sha1.digest(password.getBytes());
                    String passwordHashed = new String(byteArr);
                    Object[] arr = new Object[]{login, passwordHashed, haveAcc};
                    Commands cmd = new Commands(CommandsEnum.RETURN_ID, arr);
                    Send.send(cmd, addr, dc);
                    String answer = Receive.receive(dc);
                    try{
                        userId = Integer.parseInt(answer);
                        logged = true;
                        if (haveAcc){
                            System.out.println("Вы успешно зашли в приложение под логином: " + login);
                        }else{
                            System.out.println("Вы успешно создали аккаунт с логином: " + login);
                        }
                    }catch (Exception e){
                        System.out.println(answer);
                        continue;
                    }
                }
                System.out.println("Введите команду: ");

                String command = scanner.nextLine();

                try{
                    if(command.split(" ")[0].equals("execute_script")){
                        ArrayList<Commands> arrayList = ExecuteScript.execute(command.split(" ")[1], userId);
                        for(Commands commands: arrayList){
                            Send.send(commands,addr, dc);
                            System.out.println(Receive.receive(dc));
                        }
                        continue;
                    }
                    Commands cmd = FindCommand.findCommand(command, userId);
                    Send.send(cmd, addr, dc);
                    /*byte[] commandBytes = Commands.serializeCommand(cmd);
                    ByteBuffer buf1= ByteBuffer.wrap(commandBytes);
                    dc.send(buf1, addr);*/
                    /*byte[] serverAnswer = new byte[4096];
                    ByteBuffer buffer= ByteBuffer.wrap(serverAnswer);
                    dc.socket().setSoTimeout(300);
                    dc.read(buffer);
                    buffer.flip();
                    ArrayList <Byte> ab = new ArrayList <>();
                    while (buffer.hasRemaining())
                    {
                        byte b =buffer.get();
                        ab.add(b);
                    }

                    byte[] outDataForEncoder = new byte[ab.size()];
                    for(int i=0; i<ab.size();i++){
                        outDataForEncoder [i]=ab.get(i);
                    }
                    buffer.clear();
                    System.out.println(new String(outDataForEncoder));*/
                    System.out.println(Receive.receive(dc));

                    if (command.equals("exit")){
                        dc.close();
                        break;
                    }
                }catch (NoSuchCommand e){
                    System.out.println(e.getMessage());
                }
            }
        }catch (NoSuchElementException e){

            System.exit(0);

        }catch (IOException e) {
            System.out.println("Сервер не отвечает повторите попытку позже");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}