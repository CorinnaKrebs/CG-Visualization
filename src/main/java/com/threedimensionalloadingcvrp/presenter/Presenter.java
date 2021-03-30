package com.threedimensionalloadingcvrp.presenter;

import com.threedimensionalloadingcvrp.model.Item;
import com.threedimensionalloadingcvrp.view.components.Axes;
import com.threedimensionalloadingcvrp.view.components.Form;
import com.threedimensionalloadingcvrp.view.components.Stack;
import javafx.beans.binding.Bindings;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.SubScene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import com.threedimensionalloadingcvrp.model.ItemModel;
import com.threedimensionalloadingcvrp.view.View;
import com.threedimensionalloadingcvrp.view.components.Table;

import java.util.stream.DoubleStream;

/**
 * The type Presenter.
 */
public class Presenter {
    /** The Model. */
    private final ItemModel model;
    /** The View. */
    private final View view;

    // Center of Gravity Calculation
    /** The Sum of Mass_i * x_i of Item i. */
    private static double sumMassX = 0;

    /** The Sum of Mass_i * y_i of Item i. */
    private static double sumMassY = 0;

    /** The Sum of Mass_i * z_i of Item i. */
    private static double sumMassZ = 0;

    /** The Sum of Item Masses. */
    private static double sumMass = 0;

    // Mouse Control Variable
    /** Current x-Position of the Mouse. */
    private static double mousePosX;

    /** Current y-Position of the Mouse. */
    private static double mousePosY;

    /** Old x-Position of the Mouse. */
    private static double mouseOldX;

    /** Old y-Position of the Mouse. */
    private static double mouseOldY;

    /**
     * Instantiates a new Presenter.
     *
     * @param model the model
     * @param view  the view
     */
    public Presenter(final ItemModel model, final View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Initialize the view.
     * Adds Events Handler and sets data.
     */
    public void init() {
        addSceneMouseEvents();
        createTableColumns();
        createForm();
        handleModelChanges();
    }

    /**
     * Handle Changes in the model.
     * Update Center of Gravity.
     * Update Axes Planes if necessary.
     */
    private void handleModelChanges() {
        model.getItems().addListener((ListChangeListener.Change<? extends Item> change) -> {
            while (change.next()) {
                if (change.wasUpdated()) {
                    recalculateCG(model.getItems());
                }

                // Items are added
                for (Item item : change.getAddedSubList()) {
                    view.getStack().getChildren().add(item);
                    addOrRemoveItemForCG(item, true);
                }

                // Items are removed
                for (Item item : change.getRemoved()) {
                    view.getStack().getChildren().remove(item);
                    addOrRemoveItemForCG(item, false);
                }
            }
            view.getCenterOfGravity().toFront();

            // Get Maximum Borders of all Items
            int maxX = model.getItems().stream().mapToInt(i -> i.getX() + i.getL()).max().orElse(1);
            int maxY = model.getItems().stream().mapToInt(i -> i.getY() + i.getW()).max().orElse(1);
            int maxZ = model.getItems().stream().mapToInt(i -> i.getZ() + i.getH()).max().orElse(1);
            int max = (int) DoubleStream.of(maxX, maxY, maxZ).max().getAsDouble();
            // Maximum Value exceeds current Planes
            if (max >= Axes.getDim()) {
                // Update Axes Planes
                Axes.createAxes(max);
            }
        });
    }

    /**
     * Reset and Recalculate Center of Gravity Values.
     */
    private void recalculateCG(ObservableList<Item> items) {
        // Reset
        sumMass  = 0;
        sumMassX = 0;
        sumMassY = 0;
        sumMassZ = 0;
        // Add all Items
        for (var item : items) {
            addOrRemoveItemForCG(item, true);
        }
    }

    /**
     * Update Center of Gravity Parameters and its position.
     */
    private void addOrRemoveItemForCG(Item item, final boolean add) {
        int factor = add ? 1 : -1;
        sumMass  += factor * item.getMass();
        sumMassX += factor * item.getMass() * (item.getX() + item.getL() * 0.5);
        sumMassY += factor * item.getMass() * (item.getY() + item.getW() * 0.5);
        sumMassZ += factor * item.getMass() * (item.getZ() + item.getH() * 0.5);
        int x = (int) (sumMassX / sumMass);
        int y = (int) (sumMassY / sumMass);
        int z = (int) ((int) sumMassZ / sumMass);
        view.getCenterOfGravity().setPosition(x, y, z);
    }

    /**
     * Add Listeners to Form.
     */
    private void createForm() {
        Form form = view.getForm();
        createNumField(form.getTextLength(), true);
        createNumField(form.getTextWidth(), true);
        createNumField(form.getTextHeight(), true);
        createNumField(form.getTextMass(), false);
        createNumField(form.getTextX(), true);
        createNumField(form.getTextY(), true);
        createNumField(form.getTextZ(), true);

        form.getColorPicker().setValue(new Color(0.5, 1.0, 0, 1));
        form.getAddButton().setOnMouseClicked(event -> buttonAddItem());
    }

    /**
     * Modify TextField to allow only numbers.
     */
    private static void createNumField(TextField field, boolean isInteger) {
        // Accepts only numbers
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (isInteger && !newValue.matches("\\d*")) {
                field.setText(newValue.replaceAll("[^\\d]", ""));
            } else if (!newValue.matches("\\d{0,7}([.]\\d{0,4})?")) {
                field.setText(oldValue);
            }
        });
    }


    /**
     * Define Event When Clicking Add Button.
     */
    private void buttonAddItem() {
        Item item = createNewItem();
        clearForm();
        if (item != null) {
            model.getItems().add(item);
        }
    }

    /**
     * Read TextFields of Form.
     * @return item the new created Item
     */
    private Item createNewItem() {
        Form form = view.getForm();

        // Get Values from NumFields
        int length = Integer.parseInt(form.getTextLength().getText());
        int width = Integer.parseInt(form.getTextWidth().getText());
        int height = Integer.parseInt(form.getTextHeight().getText());
        double mass = Double.parseDouble(form.getTextMass().getText());
        int x = Integer.parseInt(form.getTextX().getText());
        int y = Integer.parseInt(form.getTextY().getText());
        int z = Integer.parseInt(form.getTextZ().getText());
        Color color = form.getColorPicker().getValue();

        // Check Feasibility of Values
        if (length > 0 && width > 0 && height > 0 && mass > 0) {
            return new Item(length, width, height, mass, x, y, z, color);
        }
        return null;
    }

    /**
     * Clear TextFields of Form.
     */
    private void clearForm() {
        Form form = view.getForm();
        form.getTextLength().clear();
        form.getTextWidth().clear();
        form.getTextHeight().clear();
        form.getTextMass().clear();
        form.getTextX().clear();
        form.getTextY().clear();
        form.getTextZ().clear();
        form.getColorPicker().setValue(new Color(Math.random(), Math.random(), Math.random(), 1.0));

    }

    /**
     * Create Table.
     * Add Listeners to Table.
     * Set Values to Columns.
     */
    private void createTableColumns() {
        Table table = view.getTable();
        table.setItems(model.getItems());
        table.prefHeightProperty().bind(Bindings.size(this.model.getItems()).multiply(table.getFixedCellSize()).add(35));

        table.getColumnLength().setCellValueFactory(i -> i.getValue().lProperty().asObject());
        table.getColumnLength().setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        table.getColumnLength().setOnEditCommit(event -> event.getRowValue().setL(event.getNewValue()));

        table.getColumnWidth().setCellValueFactory(i -> i.getValue().wProperty().asObject());
        table.getColumnWidth().setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        table.getColumnWidth().setOnEditCommit(event -> event.getRowValue().setW(event.getNewValue()));

        table.getColumnHeight().setCellValueFactory(i -> i.getValue().hProperty().asObject());
        table.getColumnHeight().setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        table.getColumnHeight().setOnEditCommit(event -> event.getRowValue().setH(event.getNewValue()));

        table.getColumnMass().setCellValueFactory(i -> i.getValue().massProperty().asObject());
        table.getColumnMass().setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        table.getColumnMass().setOnEditCommit(event -> event.getRowValue().setMass(event.getNewValue()));

        table.getColumnX().setCellValueFactory(i -> i.getValue().xProperty().asObject());
        table.getColumnX().setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        table.getColumnX().setOnEditCommit(event -> event.getRowValue().setX(event.getNewValue()));

        table.getColumnY().setCellValueFactory(i -> i.getValue().yProperty().asObject());
        table.getColumnY().setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        table.getColumnY().setOnEditCommit(event -> event.getRowValue().setY(event.getNewValue()));

        table.getColumnZ().setCellValueFactory(i -> i.getValue().zProperty().asObject());
        table.getColumnZ().setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        table.getColumnZ().setOnEditCommit(event -> event.getRowValue().setZ(event.getNewValue()));

        table.getColumnColor().setCellValueFactory(i -> i.getValue().getColorProperty());
        createColumnColor(table.getColumnColor());

        addTableRemoveContext();
    }

    /**
     * Create Colored Table Cells.
     */
    private void createColumnColor(TableColumn<Item, Color> column) {
        column.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Color color, boolean empty) {
                super.updateItem(color, empty);

                if (color == null || empty) {
                    setText("");
                    setStyle("");
                } else {
                    String hex = String.format( "#%02X%02X%02X",
                            (int)( color.getRed() * 255 ),
                            (int)( color.getGreen() * 255 ),
                            (int)( color.getBlue() * 255 ) );
                    setStyle("-fx-background-color:" + hex);
                }
            }
        });
    }

    /**
     * Add Menu Context to Remove Rows.
     */
    private void addTableRemoveContext() {
        view.getTable().setRowFactory(tableView -> {
            final TableRow<Item> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem removeMenuItem = new MenuItem("Remove");
            removeMenuItem.setOnAction(event -> model.getItems().remove(row.getItem()));
            contextMenu.getItems().add(removeMenuItem);
            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu)
            );
            return row;
        });
    }

    /**
     * Add Mouse Controls to SubScene (3D Area).
     */
    private void addSceneMouseEvents() {
        SubScene scene = view.getSubScene();
        Stack stack = view.getStack();

        // Save Mouse Position when Start Pressing
        scene.setOnMousePressed(event -> {
        mousePosX = event.getX();
        mousePosY = event.getY();
        mouseOldX = event.getX();
        mouseOldY = event.getY();
        });

        // Scroll => Scale the Stack
        scene.setOnScroll(event -> {
        double factor = event.getDeltaY() < 0 ? 0.75 : 1.25;
        double newScale = stack.getScale().getX() * factor;
        stack.getScale().setX(newScale);
        stack.getScale().setY(newScale);
        stack.getScale().setZ(newScale);
        });

        scene.setOnMouseDragged(event -> {
        mouseOldX = mousePosX;
        mouseOldY = mousePosY;
        mousePosX = event.getX();
        mousePosY = event.getY();
        double mouseDeltaX = mousePosX - mouseOldX;
        double mouseDeltaY = mousePosY - mouseOldY;

        // Primary Button => Move the Stack
        if (event.isPrimaryButtonDown()) {
        stack.setTranslateX(stack.getTranslateX() + mouseDeltaX);
        stack.setTranslateY(stack.getTranslateY() + mouseDeltaY);
        }

        // Secondary Button => Rotate the Stack around origin
        else if (event.isSecondaryButtonDown()) {
        stack.getRx().setAngle(stack.getRx().getAngle() + mouseDeltaY);
        stack.getRy().setAngle(stack.getRy().getAngle() - mouseDeltaX);
        }
        });
    }

}
