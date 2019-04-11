package server_2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipAll {
    public static void zip(List<String> list, File out) throws FileNotFoundException , IOException {
        FileOutputStream fos = new FileOutputStream(out);
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        for(String srcFile : list){
            File fileToZip = new File(srcFile);
            FileInputStream fis = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while((length =fis.read(bytes))>=0){
                zipOut.write(bytes,0,length);
            }
            fis.close();
        }
        zipOut.close();
        fos.close();
    }

}
