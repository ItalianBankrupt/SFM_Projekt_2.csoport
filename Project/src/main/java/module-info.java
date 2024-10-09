module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires java.sql;
    requires com.h2database;

    opens org.example to javafx.fxml;
    exports org.example;
}
