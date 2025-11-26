module org.varun.quizclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires java.net.http;


    opens org.varun.quizclient.controller to javafx.fxml;
    exports org.varun.quizclient;
}