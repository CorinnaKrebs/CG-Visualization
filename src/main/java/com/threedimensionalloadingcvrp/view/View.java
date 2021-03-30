package com.threedimensionalloadingcvrp.view;

import com.threedimensionalloadingcvrp.App;
import com.threedimensionalloadingcvrp.view.components.*;
import javafx.geometry.Insets;
import javafx.scene.DepthTest;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;
import lombok.Getter;

/**
 * The View.
 */
@Getter
public class View extends SplitPane {
    /** The SubScene (3D Area). */
    private final SubScene subScene;

    /** Center of Gravity. */
    private final CenterOfGravity centerOfGravity;

    /** The Axes Group. */
    private final Axes axes;

    /** The Group containing all 3D shapes. */
    private final Stack stack;

    /** The Table to modify or remove Items. */
    private final Table table;

    /** The Form to add new Items. */
    private final Form form;

    /**
     * Instantiates the View.
     */
    public View() {
        super();

        axes = new Axes(50);

        // Content
        stack = new Stack();
        stack.getChildren().add(axes);
        stack.setDepthTest(DepthTest.ENABLE);
        stack.reset(App.WIN_WIDTH, App.WIN_HEIGHT);

        centerOfGravity = new CenterOfGravity(2);
        stack.getChildren().add(centerOfGravity);

        // Create SubScene (3D Area)
        subScene = new SubScene(stack, App.WIN_WIDTH * 0.7, App.WIN_HEIGHT * 0.9);
        subScene.setCamera(new PerspectiveCamera());

        // Create Menu
        VBox menu = new VBox();
        form = new Form();
        table = new Table();
        menu.getChildren().addAll(table, form);
        VBox.setMargin(form, new Insets(10, 10, 10, 10));
        VBox.setMargin(table, new Insets(10, 10, 10, 10));

        // Create Layout
        SplitPane.setResizableWithParent(this, Boolean.TRUE);
        menu.prefWidthProperty().bind(this.widthProperty());
        getItems().addAll(menu, subScene);
    }
}
