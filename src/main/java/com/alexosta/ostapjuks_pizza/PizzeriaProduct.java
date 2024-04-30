package com.alexosta.ostapjuks_pizza;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
    private final List<ProductList> prodList = new ArrayList<>();

    public ProductList() {
        
    }

    public ListView<String> giveDataToListView() {
        ObservableList<Pizza> observableList = FXCollections.observableArrayList(prodList);
        ListView<String> listView = new ListView<>();
        listView.setItems(observableList);
        return listView;
    }
}
