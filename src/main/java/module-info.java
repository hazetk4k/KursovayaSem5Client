module client.tkachukkursovayaclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens client to javafx.fxml;
    exports client;
    exports client.ClassesForTables;
    opens client.ClassesForTables to javafx.fxml;
}