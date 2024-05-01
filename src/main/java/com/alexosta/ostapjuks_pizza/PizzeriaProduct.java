package com.alexosta.ostapjuks_pizza;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

public class PizzeriaProduct {

    private String imgLink;
    private String title;
    private String description;
    private int id;
    private int inStock;

    public PizzeriaProduct() {

    }

//    public ListView<String> giveDataToListView() {
//        ObservableList<Pizza> observableList = FXCollections.observableArrayList(prodList);
//        ListView<String> listView = new ListView<>();
//        listView.setItems(observableList);
//        return listView;
//    }

    //    getters
    public int getId() {
        return id;
    }

    public int getInStock() {
        return inStock;
    }

    public String getImgLink() {
        return imgLink;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
    //    setters
    public void setId(int id) {
        this.id = id;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
