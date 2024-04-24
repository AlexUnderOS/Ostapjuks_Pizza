package com.alexosta.ostapjuks_pizza;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

public class Products {
    private final List<String> prodList = new ArrayList<>();

    public Products() {
        prodList.add("Pizza");
        prodList.add("Drinks");
    }

    public ListView<String> giveDataToListView() {
        ObservableList<String> observableList = FXCollections.observableArrayList(prodList);
        ListView<String> listView = new ListView<>();
        listView.setItems(observableList);
        return listView;
    }
}
