package server_2;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class EncryptorTxt implements Encrypting{
    Encryptor encryptor = new Encryptor();
    public void decrypt(String key, File in, File out){
        Encryptor encryptor = new Encryptor();
        BufferedReader reader = null;
        try {
            FileOutputStream fos = new FileOutputStream(out);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            reader = new BufferedReader(new FileReader(in));
            String text = null;

            while ((text = reader.readLine()) != null) {
                String novi = encryptor.decrypt(key, text);
                bw.write(novi);
                bw.newLine();
            }
            bw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void encrypt(String key,File in, File out){
        BufferedReader reader = null;
        Encryptor encryptor = new Encryptor();
        try {
            FileOutputStream fos = new FileOutputStream(out);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            reader = new BufferedReader(new FileReader(in));
            String text = null;
            while ((text = reader.readLine()) != null) {
                String novi = encryptor.encrypt(key, text);
                bw.write(novi);
                bw.newLine();
            }
            bw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
