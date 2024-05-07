package com.alexosta.ostapjuks_pizzeriaRegister.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class Category {
    public void fillCategory(ListView<String> category_listView) {
        ObservableList<String> items = FXCollections.observableArrayList();
        category_listView.setItems(items);
        items.add("Pizza");
    }
}
