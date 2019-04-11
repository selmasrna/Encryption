package server_2;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;

public class EncryptorExcel implements Encrypting{
    public Encryptor en  = new Encryptor();
    public void decrypt(String key, File in, File out) {

        try {
            InputStream is = new FileInputStream(in);

            Workbook wbInput = WorkbookFactory.create(is);
            Workbook wbOutput = new XSSFWorkbook();

            Iterator sheetInputIterator = wbInput.sheetIterator();
            DataFormatter dataFormatter = new DataFormatter();

            while (sheetInputIterator.hasNext()) {
                Sheet sheetInput = (Sheet) sheetInputIterator.next();
                Sheet sheetOutput = wbOutput.createSheet(sheetInput.getSheetName());

                int rowCounter = 0;
                int columnCounter = 0;
                Iterator rowInputIterator = sheetInput.rowIterator();
                while (rowInputIterator.hasNext()) {
                    Row rowInput = (Row) rowInputIterator.next();
                    //create a row in the output Workbook
                    Row rowOut = sheetOutput.createRow(rowCounter);
                    Iterator cellInputIterator = rowInput.cellIterator();
                    columnCounter = 0;
                    while (cellInputIterator.hasNext()) {
                        XSSFCell cell = (XSSFCell) cellInputIterator.next();
                        rowOut.createCell(columnCounter);
                        sheetOutput.getRow(rowCounter).getCell(columnCounter).setCellValue(en.decrypt(key, dataFormatter.formatCellValue(cell)));
                        columnCounter++;

                    }
                    rowCounter++;
                }
            }

            wbInput.close();
            FileOutputStream fileOutputStreams = new FileOutputStream(out);
            wbOutput.write(fileOutputStreams);

            wbOutput.close();


        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void encrypt(String key, File in, File out) {

        try {
            InputStream is = new FileInputStream(in);

            Workbook wbInput = WorkbookFactory.create(is);
            Workbook wbOutput = new XSSFWorkbook();

            Iterator sheetInputIterator = wbInput.sheetIterator();
            DataFormatter dataFormatter = new DataFormatter();

            while (sheetInputIterator.hasNext()) {
                Sheet sheetInput = (Sheet) sheetInputIterator.next();
                Sheet sheetOutput = wbOutput.createSheet(sheetInput.getSheetName());

                int rowCounter = 0;
                int columnCounter = 0;
                Iterator rowInputIterator = sheetInput.rowIterator();
                while (rowInputIterator.hasNext()) {
                    Row rowInput = (Row) rowInputIterator.next();
                    //create a row in the output Workbook
                    Row rowOut = sheetOutput.createRow(rowCounter);
                    Iterator cellInputIterator = rowInput.cellIterator();
                    columnCounter = 0;
                    while (cellInputIterator.hasNext()) {
                        XSSFCell cell = (XSSFCell) cellInputIterator.next();
                        rowOut.createCell(columnCounter);
                        sheetOutput.getRow(rowCounter).getCell(columnCounter).setCellValue(en.encrypt(key, dataFormatter.formatCellValue(cell)));
                        columnCounter++;

                    }
                    rowCounter++;
                }
            }

            wbInput.close();
            FileOutputStream fileOutputStreams = new FileOutputStream(out);
            wbOutput.write(fileOutputStreams);

            wbOutput.close();


        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
