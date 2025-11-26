module org.varun.quizclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.varun.quizclient to javafx.fxml;
    exports org.varun.quizclient;
}