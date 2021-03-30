package com.threedimensionalloadingcvrp.model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * The Item Model Class.
 * Contains the necessary Data (Repository of Item).
 * Change made in list or in an item are fired to presenter.
 */
@NoArgsConstructor
@Getter
public class ItemModel {
    private final ObservableList<Item> items = FXCollections.observableArrayList(item ->
            new Observable[] {
                    item.lProperty(), item.wProperty(), item.hProperty(), item.massProperty(),
                    item.xProperty(), item.yProperty(), item.zProperty()
            }
    );
}
