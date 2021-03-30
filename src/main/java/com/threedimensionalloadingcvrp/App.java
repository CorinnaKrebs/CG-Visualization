package com.threedimensionalloadingcvrp;

import com.threedimensionalloadingcvrp.presenter.Presenter;
import com.threedimensionalloadingcvrp.view.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.threedimensionalloadingcvrp.model.ItemModel;

/**
 * The type App.
 */
public class App extends Application {
    /**
     * The constant representing the window width.
     */
    public static final int WIN_WIDTH = 1024;
    /**
     * The constant representing the window height.
     */
    public static final int WIN_HEIGHT = 600;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch();
    }


    /**
     * Start the Application.
     *
     * @param stage the stage to show the view.
     */
    public void start(Stage stage) {
        stage.setTitle("Center of Gravity Visualization");

        // Create MVP
        View view = new View();
        ItemModel model = new ItemModel();
        Presenter presenter = new Presenter(model, view);
        presenter.init();

        // Create Scene
        Scene scene = new Scene(view, App.WIN_WIDTH, App.WIN_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
}