package client_2;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class client_2 {
    public static void main(String[] args) throws IOException {
        if (args.length!=3) {
            System.out.println("Not enough arguments.");
            System.exit(-1);
        }
        String serverAddress=args[0].toString();
        Integer portNumber =Integer.parseInt(args[1]);
        String pathToInputDir =args[2].toString();


        Socket s = new Socket(serverAddress,portNumber);
        Scanner sc1 = new Scanner(s.getInputStream());

        PrintStream p = new PrintStream(s.getOutputStream());
        p.println(pathToInputDir);

        String temp;
        temp=sc1.nextLine();
        System.out.println(temp);
    }

}
