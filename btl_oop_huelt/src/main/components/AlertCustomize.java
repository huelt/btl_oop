package main.components;
import javafx.beans.NamedArg;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AlertCustomize {
    private Alert alert;

    public AlertCustomize(Alert.AlertType type){
        alert = new Alert(type);
    }

    public AlertCustomize(Alert.AlertType type, String header, String title, String content){
        alert = new Alert(type);
        alert.setHeaderText(header);
        alert.setTitle(title);
        alert.setContentText(content);
    }

    public Alert init(String header, String title, String content){
        alert.setHeaderText(header);
        alert.setTitle(title);
        alert.setContentText(content);

        return alert;
    }

    public void show(){
        alert.show();
    }

    public Alert get(){
        return alert;
    }
}
