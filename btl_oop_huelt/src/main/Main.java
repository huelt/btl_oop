package main;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
    private DictionaryApplication router;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Global.WINDOWN = primaryStage;

        router = new DictionaryApplication();
        router.runApplication();

        Global.WINDOWN.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
