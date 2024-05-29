package com.alexosta.ostapjuks_pizzeriaRegister.view;

import com.alexosta.ostapjuks_pizzeriaRegister.model.ProductContainer;
import com.alexosta.ostapjuks_pizzeriaRegister.service.DBProduct;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Receipts {
    private final ProductContainer productContainer;

    public Receipts(ProductContainer productContainer) throws IOException {
        this.productContainer = productContainer;
        fillImageReceiptWithData();
    }

    private String randomHash;
    private String getData() {
        Date date = new Date();
        String productCart = getProductsFromCart();
        System.out.println(productCart);
        randomHash = generateRandomHash();
        double total = getTotalPriceFromCart();

        String receipt = "ČILI PICA!\n" +
                         "Product Nr.:" + randomHash + "\n" +
                         "Orders: \n" + productCart + "\n" +
                         "______________________________" + "\n" +
                         "Data: " + date + "\n" +
                         "Total: " + total + "\n" +
                         "______________________________";


        return receipt;
    }

    private double getProductPriceByNameFromDB(String productName) {

        List<String> productNames = DBProduct.getProductNameFromDatabase();

        int index = productNames.indexOf(productName);

        double price;
        if (index != -1) {
            List<Double> prices = DBProduct.getProductPriceFromDatabase();
            price = prices.get(index);
            return price;

        } else {
            return 0.0;
        }
    }

    private String getProductsFromCart() {
        List<String> selectedProducts = productContainer.getSelectedProducts();
        String result = "";
        for (int i = 0; i < selectedProducts.size(); i++) {
            double price = getProductPriceByNameFromDB(selectedProducts.get(i));
            result += selectedProducts.get(i) + " - "+ price +" euro\n";
            System.out.println(result);
        }
        return result;
    }

    private double getTotalPriceFromCart() {
        List<String> selectedProducts = productContainer.getSelectedProducts();
        double totalPrice = 0.0;
        for (String productName : selectedProducts) {
            double price = getProductPriceByNameFromDB(productName);
            totalPrice += price;
        }
        return totalPrice;
    }


    private static String generateRandomHash() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 7;
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
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

            ImageIO.write(image, "png", new File("RECEIPT_"+randomHash+".png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
