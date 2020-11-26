package View;

import Controller.Controller;
import Model.exp.ValueExp;
import Model.stmt.AssignStmt;
import Model.stmt.IStmt;
import Model.stmt.MultiStmt;
import Model.stmt.VarDeclStmt;
import Model.types.BoolType;
import Model.types.IntType;
import Model.types.RefType;
import Model.types.StringType;
import Model.vals.IntValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class CreationScene extends MyScene{
    Scene scene;
    GridPane gridPane;
    Button declareButton;
    Button arithmButton;
    Button assignButton;
    TextField varName;
    Label label;
    TextField varName1;
    TextField operator;
    TextField varName2;
    TextField varName3;
    TextField assignValue;
    ComboBox<String> typesList;
    ArrayList<IStmt> stmtArrayList;
    public CreationScene(Stage application, Controller controller) {
        super(application, controller);
        stmtArrayList = new ArrayList<>();

        label = new Label();
        typesList = new ComboBox<>();
        typesList.getItems().add("Integer");
        typesList.getItems().add("Boolean");
        typesList.getItems().add("String");
        typesList.getItems().add("Reference");

        varName = new TextField();
        varName1 = new TextField();
        operator = new TextField();
        varName2 = new TextField();
        assignValue = new TextField();
        varName3 = new TextField();

        gridPane = new GridPane();
        gridPane.setMinSize(720, 480);
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);

        declareButton = new Button("Declare");
        arithmButton = new Button("Arithmetic");
        assignButton = new Button("Assign");

        gridPane.add(declareButton,0,0);
        gridPane.add(assignButton, 0,1);
        gridPane.add(arithmButton, 0, 2);
        gridPane.add(typesList, 1,0);
        gridPane.add(varName, 2, 0);
        gridPane.add(varName1, 1, 2);
        gridPane.add(operator, 2, 2);
        gridPane.add(varName2, 3, 2);
        gridPane.add(assignValue, 2, 1);
        gridPane.add(varName3, 1, 1);
        gridPane.add(label,2, 4);
        gridPane.setBackground(new Background(new BackgroundFill(Color.DARKGREY, null,null)));

        scene = new Scene(gridPane);
        declareButton.setOnAction(e -> {
            String type = this.typesList.getSelectionModel().getSelectedItem();
            switch (type) {
                case "String" -> this.stmtArrayList.add(new VarDeclStmt(new StringType(), this.varName.getText()));
                case "Integer" -> this.stmtArrayList.add(new VarDeclStmt(new IntType(), this.varName.getText()));
                case "Boolean" -> this.stmtArrayList.add(new VarDeclStmt(new BoolType(), this.varName.getText()));
                case "Reference" -> this.stmtArrayList.add(new VarDeclStmt(new RefType(), this.varName.getText()));
            }
            this.updateMainStage();
        });
        assignButton.setOnAction(e -> {
            try{
                this.stmtArrayList.add(new AssignStmt(this.varName3.getText(), new ValueExp(new IntValue(Integer.parseInt(this.assignValue.getText())))));
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
            }
            this.updateMainStage();
        });
    }

    @Override
    void updateMainStage() {
        this.label.setText("");
        this.label.setText(new MultiStmt(this.stmtArrayList).toString());
    }
}
