package View;

import Controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class WorkScene extends MyScene{
    VBox vPane;
    Scene scene;
    ListView<String> programsList;
    Button executeButton;
    TextArea textArea;
    Button createButton;
    Text text;
    Text exeText;
    private void updateListView(){
        ArrayList<String> arrayList = this.controller.getRepository().getRepresentation();
        ObservableList<String> programs = FXCollections.observableArrayList(arrayList);
        programsList.setItems(programs);
    }
    public WorkScene(Stage mainStage, Controller controller) {
        super(mainStage, controller);
        executeButton = new Button("Execute!");
        programsList = new ListView<>();
        textArea = new TextArea();
        createButton = new Button("Create Program!");
        programsList.setOrientation(Orientation.VERTICAL);

        this.text = new Text("Your preloaded programs :");
        this.exeText = new Text("Execution of program :");
        this.vPane = new VBox(text, programsList, executeButton, exeText, textArea, createButton);
        this.vPane.setSpacing(10);
        this.vPane.setAlignment(Pos.CENTER);
        this.updateListView();
        executeButton.setOnAction(e ->
        {
            int index = this.programsList.getSelectionModel().getSelectedIndex();
            try {
                this.textArea.clear();
                String s = controller.allStepSpecific(index);
                this.textArea.setText(s);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            this.updateListView();
        });
        programsList.setMaxSize(630,150);
        this.scene = new Scene(vPane, 620, 470);
    }

    public Scene getScene() {
        return scene;
    }

    @Override
    void updateMainStage() {
    }
}
