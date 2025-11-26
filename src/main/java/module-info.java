module org.varun.quizclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens org.varun.quizclient.controller to javafx.fxml;
    exports org.varun.quizclient;
}