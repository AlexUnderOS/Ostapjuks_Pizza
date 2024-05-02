package com.alexosta.ostapjuks_pizza;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public class Receipts {
    public Receipts() {
        try {
            createReceiptFile();
            fillReceiptFile();
            System.out.println("Receipt file has been created and filled.");
        } catch (IOException e) {
            System.err.println("Error creating or filling the receipt file: " + e.getMessage());
        }
    }

    private void createReceiptFile() throws IOException {
        String filePath = "src/main/resources/com/alexosta/ostapjuks_pizza/receipts/"+generateReceiptName()+".txt";
        File receiptFile = new File(filePath);
        if (receiptFile.exists()) {
            System.out.println("not too fast!");
            return;
        }
        receiptFile.createNewFile();
        System.out.println("Receipt file created.");
    }

    private void fillReceiptFile() throws IOException {
        String filePath = "src/main/resources/com/alexosta/ostapjuks_pizza/receipts/"+generateReceiptName()+".txt";
        FileWriter writer = new FileWriter(filePath, true);


        int total;
        //need for
            writer.write("------------------------\n");
            writer.write("Product: "+ "product name\n");
            writer.write("Ingredients: "+ "product ingredients\n");
            writer.write("Size: "+ "size of product\n");
            writer.write("Price: "+ 9.99 +"EUR\n");
            writer.write("------------------------\n");

            writer.write("Total: "+ 9.99 +" EUR");
            writer.close();
            System.out.println("Receipt file filled.");
    }

    private String generateReceiptName() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH(mm_ss)");
        return now.format(formatter);    }
}
