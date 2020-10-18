package main.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.Global;
import main.components.AlertCustomize;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class HomeController {
    @FXML
    private TextField inputSearch;

    @FXML
    private VBox meanField;

    @FXML
    private ListView suggestWords;

    public void submitSearch(ActionEvent event) throws Exception {

        ObservableList<Node> texts = meanField.getChildren();
        String stringAfter = inputSearch.getText().trim();
        if(stringAfter.isEmpty()){
            AlertCustomize alertCustomize = new AlertCustomize(Alert.AlertType.INFORMATION);
            alertCustomize.init("Empty!", "422", "Please enter another word!");
            alertCustomize.show();
        }
        String first = "";
        if(!stringAfter.isEmpty()){
            first = String.valueOf(stringAfter.charAt(0));
        }

        if(Global.ALPHAS.containsKey(first)){
            String value = findWord(stringAfter, first); //FIND WORD

            if(value.isEmpty()) {
                AlertCustomize alertCustomize = new AlertCustomize(Alert.AlertType.INFORMATION);
                alertCustomize.init("Not found word!", "404", "Please enter another word!");
                alertCustomize.show();
            }
            texts.forEach(text -> {
                if (text instanceof Text) {
                    ((Text) text).setText(value);
                }
            });
        }else{
            AlertCustomize alertCustomize = new AlertCustomize(Alert.AlertType.INFORMATION);
            alertCustomize.init("Not found word!", "404", "Please enter another word!");
            alertCustomize.show();
        }

    }

    private String findWord(String word, String folder) throws Exception{
        String filePath = "src/main/assets/@"+ folder +"/@"+ folder +".txt";
        String value = "";
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        String contentALl = contentBuilder.toString();
        String[] contentRows = contentALl.split("@");

        List<String> listRows = Arrays.asList(contentRows);

        int searchListLength = listRows.size();
        int firstKey = 0;
        List<Button> labels = new ArrayList<>();

        for (int i = 0; i < searchListLength; i++) {
            String originRow = "@" + listRows.get(i);
            if (originRow.contains("@" + word)) {
                String[] suggestWordOrg = listRows.get(i).split("/");
                Button buttonEdit = new Button(suggestWordOrg[0]);
                buttonEdit.setOnAction(event -> {
                    CreateController createController = new CreateController();
                    createController.showEdit(suggestWordOrg[0],suggestWordOrg[1], suggestWordOrg[2]);
                });
                labels.add(buttonEdit);
                if(firstKey == 0) {
                    value = listRows.get(i);
                    firstKey = 1;
                }
            }
        }

        suggestWords.getItems().setAll(labels);

        return value;
    }

    public void loadSearchScene(){
        Global.WINDOWN.setScene(Global.SCENE_HOME);
    }

    public void loadCreateScene(){
        CreateController createController = new CreateController();
        createController.init();
        Global.WINDOWN.setScene(Global.SCENE_CREATE);
    }

    public void splitFile(){
        Map<String, String> alphas = new HashMap<String, String>()
        {{
            put("@a", "a"); put("@b", "b"); put("@c", "c"); put("@d", "d");
            put("@e", "e"); put("@f", "f"); put("@g", "g"); put("@h", "h");
            put("@i", "i"); put("@j", "j"); put("@k", "k"); put("@l", "l");
            put("@m", "m"); put("@n", "n"); put("@o", "o"); put("@p", "p");
            put("@q", "q"); put("@r", "r"); put("@s", "s"); put("@t", "t");
            put("@u", "u"); put("@v", "v"); put("@w", "w"); put("@x", "x");
            put("@y", "y"); put("@z", "z");
        }};
        try {
            for(Map.Entry<String, String> entry : alphas.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                File directory = new File("src/main/assets/" + key +"/");
                if (! directory.exists()){
                    directory.mkdir();
                }
            }

            FileReader reader = new FileReader("src/main/assets/anhviet109k.txt");
            int character;

            for(Map.Entry<String, String> entry : alphas.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();

                FileWriter writer = new FileWriter("src/main/assets/" + key + "/" + key + ".txt");
                if(!value.equals("a")){
                    writer.append('@');
                    writer.append(value.charAt(0));
                }

                while ((character = reader.read()) != -1) {
                    int nextInt;
                    char var = (char) character;

                    if(var != '@'){
                        writer.append((char) character);
                    }else{
                        if((nextInt = reader.read()) != -1){
                            char nextVar = (char) nextInt;
                            String nextString = String.valueOf(nextVar);

                            if(nextString.equals(value)){
                                writer.append('@');
                                writer.append(nextVar);
                            }else{
                                break;
                            }
                        }else{
                            writer.append((char) character);
                            break;
                        }
                    }
                }
                writer.flush();
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            AlertCustomize alertCustomize1 = new AlertCustomize(Alert.AlertType.INFORMATION);
            alertCustomize1.init("Success", "Notification", "Reset all data success.<3");
            alertCustomize1.show();
        }
    }
}
