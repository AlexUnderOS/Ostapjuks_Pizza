package com.alexosta.ostapjuks_pizza;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

public class Category {
    public void fillCategory(ListView<String> category_listView) {
        ObservableList<String> items = FXCollections.observableArrayList();
        category_listView.setItems(items);
        items.add("Pizza");
    }
}
