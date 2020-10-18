package main.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.Global;
import main.components.AlertCustomize;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class CreateController {
    @FXML
    private TextField englishWord;

    @FXML
    private TextArea vietnamWord;

    @FXML
    private TextField phonetic;

    public void saveWord(){
        String eng = englishWord.getText().trim();
        String vi = vietnamWord.getText().trim();

        if(eng.isEmpty() || vi.isEmpty()) {
            AlertCustomize alertCustomize = new AlertCustomize(Alert.AlertType.INFORMATION);
            alertCustomize.init("Warning!", "422", "Word is require.");
            alertCustomize.show();
        }else{
            boolean valid = Global.ALPHAS.containsKey(String.valueOf(eng.charAt(0)));
            if(!valid){
                AlertCustomize alertCustomize = new AlertCustomize(Alert.AlertType.INFORMATION);
                alertCustomize.init("Warning!", "422", "Word is invalid.");
                alertCustomize.show();
            }else {
                if(save(String.valueOf(eng.charAt(0)), eng, vi)){
                    AlertCustomize alertCustomize = new AlertCustomize(Alert.AlertType.CONFIRMATION);
                    alertCustomize.init("Success!", "200", "Word was saved.");
                    alertCustomize.show();
                };
            }
        }
    }

    private boolean save(String folder, String eng, String vi){
        String filePath = "src/main/assets/@"+ folder +"/@"+ folder +".txt";
        String value = "";
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> {
                if(s.contains("@" + eng)){
                    contentBuilder.append("@***").append("\n");
                }else {
                    contentBuilder.append(s).append("\n");
                }
            });
            contentBuilder.append("@" + eng + " / " + phonetic.getText().trim() + " / \n" + vi + "\n");
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(contentBuilder.toString());
            fileWriter.flush();
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public void deleteWord(){
        String folder = String.valueOf(englishWord.getText().trim().charAt(0));
        String filePath = "src/main/assets/@"+ folder +"/@"+ folder +".txt";
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> {
                String textCheck = "@" + englishWord.getText().trim();
                if(s.contains(textCheck)){
                    String textOrg = s.split("/")[0];
                    if(textOrg.trim().equals(textCheck)){
                        System.out.println(s.split("/")[0]);
                        contentBuilder.append("@***").append("\n");
                    }else{
                        contentBuilder.append(s).append("\n");
                    }
                }else {
                    contentBuilder.append(s).append("\n");
                }
            });
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(contentBuilder.toString());
            fileWriter.flush();

            AlertCustomize alertCustomize = new AlertCustomize(Alert.AlertType.CONFIRMATION);
            alertCustomize.init("Success!", "200", "Word was deleted.");
            alertCustomize.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void showEdit(String engOld, String phoneticOld, String viOld){
        TextField en = (TextField) Global.SCENE_CREATE.lookup("#englishWord");
        TextField pho = (TextField) Global.SCENE_CREATE.lookup("#phonetic");
        TextArea vi = (TextArea) Global.SCENE_CREATE.lookup("#vietnamWord");
        Label title = (Label) Global.SCENE_CREATE.lookup("#titleCreate");
        Button buttonEdit = (Button) Global.SCENE_CREATE.lookup("#save");
        Button buttonDelete = (Button) Global.SCENE_CREATE.lookup("#deleteButton");
        en.setText(engOld);
        en.setDisable(true);
        pho.setText(phoneticOld);
        vi.setText(viOld);
        title.setText("EDIT WORD");
        buttonEdit.setText("Edit");
        buttonDelete.setVisible(true);
        Global.WINDOWN.setScene(Global.SCENE_CREATE);
    }

    public void init(){
        TextField en = (TextField) Global.SCENE_CREATE.lookup("#englishWord");
        TextField pho = (TextField) Global.SCENE_CREATE.lookup("#phonetic");
        TextArea vi = (TextArea) Global.SCENE_CREATE.lookup("#vietnamWord");
        Label title = (Label) Global.SCENE_CREATE.lookup("#titleCreate");
        Button buttonEdit = (Button) Global.SCENE_CREATE.lookup("#save");
        Button buttonDelete = (Button) Global.SCENE_CREATE.lookup("#deleteButton");
        en.setText("");
        en.setDisable(false);
        pho.setText("");
        vi.setText("");
        buttonEdit.setText("Save");
        buttonDelete.setVisible(false);
        title.setText("CREATE NEW WORD");
    }

    public void loadSearchScene(){
        Global.WINDOWN.setScene(Global.SCENE_HOME);
    }
}
