package com.alexosta.ostapjuks_pizzeriaRegister.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Receipts {

    public Receipts() throws IOException {
        fillImageReceiptWithData();
    }

    private String getData() {
        Date date = new Date();
        String productAndPrice = getProductAndPriceList();
        String hashName = generateRandomHash(10);
        double total = 10;

        String receipt = "ČILI PICA!\n" +
                         "Product Nr.:" + hashName + "\n" +
                         "Orders: " + productAndPrice + "\n" +
                         "______________________________" + "\n" +
                         "Data: " + date + "\n" +
                         "Total: " + total + "\n" +
                         "______________________________";


        return receipt;
    }

    private String getProductAndPriceList() {

        return "sdasda";
    }

    public static String generateRandomHash(int length) {
        StringBuilder hash = new StringBuilder();
        try {
            SecureRandom random = SecureRandom.getInstanceStrong();

            byte[] randomBytes = new byte[length];
            random.nextBytes(randomBytes);

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(randomBytes);

            for (byte b : hashBytes) {
                hash.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash.toString();
    }

    private void fillImageReceiptWithData() throws IOException {
        try {
            final BufferedImage image = ImageIO.read(
                    new File("src/main/resources/com/alexosta/ostapjuks_pizzeriaRegister/UI/imgs/receipt_template.png"));

            Graphics2D g = (Graphics2D) image.getGraphics();
            g.setFont(g.getFont().deriveFont(60f));
            g.setColor(Color.BLACK);

            String data = getData();
            int x = 100;
            int y = 200;
            int lineHeight = g.getFontMetrics().getHeight();

            // split lines
            String[] lines = data.split("\n");

            // draw every line
            for (String line : lines) {
                g.drawString(line, x, y);
                y += lineHeight;
            }

            g.dispose();

            ImageIO.write(image, "png", new File("test.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
