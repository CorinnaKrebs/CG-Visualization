package com.threedimensionalloadingcvrp.view.components;

import com.threedimensionalloadingcvrp.model.Item;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * The Table displaying the properties of all items.
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class Table extends TableView<Item> {
    /** Table Column showing Item Length. */
    private final TableColumn<Item, Integer> columnLength;

    /** Table Column showing Item Width. */
    private final TableColumn<Item, Integer> columnWidth;

    /** Table Column showing Item Height. */
    private final TableColumn<Item, Integer> columnHeight;

    /** Table Column showing Item Mass. */
    private final TableColumn<Item, Double> columnMass;

    /** Table Column showing x-Position of Item. */
    private final TableColumn<Item, Integer> columnX;

    /** Table Column showing y-Position of Item. */
    private final TableColumn<Item, Integer> columnY;

    /** Table Column showing z-Position of Item. */
    private final TableColumn<Item, Integer> columnZ;

    /** Table Column displaying used Color of Item. */
    private final TableColumn<Item, Color>  columnColor;

    /**
     * Instantiates the Table.
     */
    public Table() {
        super();
        setFixedCellSize(30);
        setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        columnLength = new TableColumn<>("Length");
        columnWidth = new TableColumn<>("Width");
        columnHeight = new TableColumn<>("Height");
        columnMass = new TableColumn<>("Mass");
        columnX = new TableColumn<>("x");
        columnY = new TableColumn<>("y");
        columnZ = new TableColumn<>("z");
        columnColor = new TableColumn<>("Color");

        getColumns().addAll(columnLength, columnWidth, columnHeight, columnMass, columnX, columnY, columnZ, columnColor);

        setEditable(true);
    }
}
