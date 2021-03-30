module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;

    opens com.threedimensionalloadingcvrp to javafx.fxml;
    exports com.threedimensionalloadingcvrp;
}