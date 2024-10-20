module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires java.sql;
    requires com.h2database;
    requires org.hibernate.orm.core;

    opens org.example to javafx.fxml;
    opens org.example.Cassa to javafx.fxml, org.hibernate.orm.core;
    opens org.example.Restaurant to javafx.fxml, org.hibernate.orm.core;
    opens org.example.Services to javafx.fxml, org.hibernate.orm.core;

    exports org.example.Restaurant;
    exports org.example;
    exports org.example.Cassa;
    exports org.example.Services;
}
