package com.threedimensionalloadingcvrp.view.components;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * The Form to get all necessary values
 * to create a new Item.
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class Form extends GridPane {
    /** TextField to enter Item Length. */
    private final TextField textLength;

    /** TextField to enter Item Width. */
    private final TextField textWidth;

    /** TextField to enter Item Height. */
    private final TextField textHeight;

    /** TextField to enter Item Mass. */
    private final TextField textMass;

    /** TextField to enter x-Position of Item. */
    private final TextField textX;

    /** TextField to enter y-Position of Item. */
    private final TextField textY;

    /** TextField to enter z-Position of Item. */
    private final TextField textZ;

    /** Field to define the Color of the Item. */
    private final ColorPicker colorPicker;

    /** Button to Add Items. */
    private final Button addButton;

    /**
     * Create the Form.
     */
    public Form() {
        super();
        setHgap(5);
        setVgap(10);

        // Add Labels in First Column
        int row = 0;
        add(new Label("Characteristics"), 0, row);
        add(new Label("Length"), 0, ++row);
        add(new Label("Width"), 0, ++row);
        add(new Label("Height"), 0, ++row);
        add(new Label("Mass"), 0, ++row);
        add(new Label("Position"), 0, ++row);
        add(new Label("x"), 0, ++row);
        add(new Label("y"), 0, ++row);
        add(new Label("z"), 0, ++row);
        add(new Label("Color"), 0, ++row);


        // Add TextFields in Second Column
        row = 0;

        textLength = new TextField();
        add(textLength, 1, ++row);

        textWidth = new TextField();
        add(textWidth, 1, ++row);

        textHeight = new TextField();
        add(textHeight, 1, ++row);

        textMass = new TextField();
        add(textMass, 1, ++row);

        ++row;

        textX = new TextField();
        add(textX, 1, ++row);

        textY = new TextField();
        add(textY, 1, ++row);

        textZ = new TextField();
        add(textZ, 1, ++row);

        colorPicker = new ColorPicker();
        add(colorPicker, 1, ++row);

        // Button
        addButton = new Button("Add");

        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.getChildren().add(addButton);
        add(hBox, 1, ++row);

    }
}
