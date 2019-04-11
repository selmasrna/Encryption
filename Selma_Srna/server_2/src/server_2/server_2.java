package server_2;

import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class server_2 {
    public static void main(String[] args) throws InvalidKeySpecException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException {

        if(args.length!=3) {
            System.err.println("Not enough arguments!");
            System.exit(-1);
        }
        Integer portNumber =Integer.parseInt(args[0]);
        String pathToDatabase = args[1].toString();
        String pathToOutputDir =args[2].toString();

        EncryptorExcel encryptorExcel = new EncryptorExcel();
        HashMap<String,String> hmap = new HashMap<String, String>();
        String pathToInputDir;
        String key;
        SqliteManager sq = new SqliteManager();
        sq.Manager(pathToDatabase);

        ServerSocket s1 = new ServerSocket(portNumber);

        Socket ss = s1.accept();
        PrintStream p =new PrintStream(ss.getOutputStream());

        Scanner sc = new Scanner(ss.getInputStream());
        pathToInputDir = sc.nextLine();
        System.out.println(pathToInputDir);


        hmap = sq.executeQ();
        File folder = new File(pathToInputDir);
        File[] listOfFiles = folder.listFiles();

        List<String> niz = new ArrayList<String>();
        File out = new File(pathToOutputDir+"\\Selma_Srna.zip");

        EncryptorTxt encryptorTxt=new EncryptorTxt();


        for (File file : listOfFiles) {
            if (file.isFile()) {
                if(file.getName().contains(".xlsx")){
                    if(!hmap.containsKey(file.getName())) {
                        System.err.println("No key for decryption in database.");
                        p.println("Failure");
                        continue;
                    }
                    key=hmap.get(file.getName());
                    File file1 = new File(pathToOutputDir+"\\"+file.getName());
                    encryptorExcel.decrypt(key,file,file1);
                    niz.add(file1.getPath());
                } else{
                    if(!hmap.containsKey(file.getName())) {
                        System.err.println("No key for decryption in database.");
                        p.println("Failure");
                        continue;
                    }
                    key=hmap.get(file.getName());
                    File file1 = new File(pathToOutputDir+"\\"+file.getName());
                    encryptorTxt.decrypt(key,file,file1);
                    niz.add(file1.getPath());
                }
            }
        }


        ZipAll.zip(niz, out);

        p.println("Success");
    }

}
