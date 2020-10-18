package main;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import main.controllers.HomeController;

public class DictionaryApplication {

    private HomeController homeController;

    public DictionaryApplication(){
        loadLoginScene();
    }

    public void runApplication() throws Exception{
        //Home scene
        Parent home = FXMLLoader.load(getClass().getResource("main.fxml"));
        Global.SCENE_HOME = new Scene(home, 715, 557);
        Global.SCENE_HOME.getStylesheets().add(getClass().getResource("main.css").toExternalForm());

        //Create scene
        Parent create = FXMLLoader.load(getClass().getResource("create.fxml"));
        Global.SCENE_CREATE = new Scene(create, 714, 556);

        Global.WINDOWN.setScene(Global.SCENE_LOGIN);
    }

    public void loadLoginScene(){
        Label label = new Label("Welcome !");

        Button buttonStart = new Button("Goto app");
        buttonStart.setOnAction(event -> {
            Global.WINDOWN.setScene(Global.SCENE_HOME);
        });

        VBox layoutLogin = new VBox();
        layoutLogin.setAlignment(Pos.CENTER);
        layoutLogin.getChildren().addAll(label, buttonStart);
        Global.SCENE_LOGIN = new Scene(layoutLogin, 715, 557);
    }
}
