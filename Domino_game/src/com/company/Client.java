package com.company;

import com.company.model.Command;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class Client{
   private final String host;
    private final int port;

    public static void main(String[] args) throws IOException {
        try {
            Client client = new Client(args[1], Integer.parseInt(args[0]));
            client.start();
        }catch (ConnectException ex){
            System.out.println("Oops!! I can't to connect to server.");
        }
    }
    public Client (String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws IOException {
        Socket socket = new Socket(host, port);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        String command;
       while (!socket.isClosed() && (command = in.readLine()) != null) {
            String[] parsed = command.split(Command.SEPARATOR);
            if (Command.StartPlayer.getCommandString().equals(parsed[0])){
                System.out.print("From server: начинает игрок: "+parsed[1]+"\n");
            }
           if (Command.Message.getCommandString().equals(parsed[0])){
               System.out.print("From server: сообщение: "+parsed[1]+"\n");
           }
           if (Command.MessageGameOver.getCommandString().equals(parsed[0])){
               System.out.print("From server: сообщение: "+parsed[1]+"\n");
           }
           if (Command.ErrorMessage.getCommandString().equals(parsed[0])){
               System.out.print("From server: предупреждение: "+parsed[1]+"\n");
           }
           if (Command.PrintBoard.getCommandString().equals(parsed[0])){
               System.out.print("From server:\n------**Поле**-----\n"+parsed[1]+"\n-----**----**-----\n");
           }
           if (Command.PrintDomino.getCommandString().equals(parsed[0])){
               System.out.print("From server:\n"+parsed[1]+"\n");
           }
            if (Command.RespInt.getCommandString().equals(parsed[0])) {
               System.out.print(Command.RespInt.getCommandString());
               Scanner scan = new Scanner(System.in);
                int nextInt;
               try {
                   nextInt = Integer.parseInt(scan.nextLine());
               }catch (NumberFormatException ex){
                   nextInt = 9;
               }
                   String resp = Command.RespInt.getCommandString()+ Command.SEPARATOR + nextInt;
                   out.println(resp);
            }
           if (Command.RespStr.getCommandString().equals(parsed[0])) {
               System.out.print(Command.RespStr.getCommandString());
               String nextLine = new Scanner(System.in).nextLine();
               String resp = Command.RespStr.getCommandString()+ Command.SEPARATOR + nextLine;
               System.out.println("To server:"+resp);
               out.println(resp);
           }
           if (Command.RespStrROrL.getCommandString().equals(parsed[0])) {
               System.out.print(Command.RespStrROrL.getCommandString());
               String nextLine = new Scanner(System.in).nextLine();
               String resp = Command.RespStrROrL.getCommandString()+ Command.SEPARATOR + nextLine;
               System.out.println("To server:"+resp);
               out.println(resp);
           }
           if (Command.RespIsReverse.getCommandString().equals(parsed[0])) {
               System.out.print(Command.RespIsReverse.getCommandString());
               String nextLine = new Scanner(System.in).nextLine();
               String resp = Command.RespIsReverse.getCommandString()+ Command.SEPARATOR + nextLine;
               System.out.println("To server:"+resp);
               out.println(resp);
           }
           if (Command.TotalHOD.getCommandString().equals(parsed[0])){
               System.out.print("From server:\nОбщее количество ходов: "+parsed[1]+"\n");
           } if (Command.FinalPrintDomino.getCommandString().equals(parsed[0])){
               System.out.print("From server:\n"+parsed[1]+"\n");
           } if (Command.Fish.getCommandString().equals(parsed[0])){
               System.out.print("From server:\n"+parsed[1]+"\n");
           } if (Command.Winner.getCommandString().equals(parsed[0])){
               System.out.print("From server:\n"+parsed[1]+"\n");
           } if (Command.END.getCommandString().equals(parsed[0])){
               System.out.print("From server:\n"+parsed[1]+"\n");
               out.close();
               socket.close();
           }
        }
    }
}