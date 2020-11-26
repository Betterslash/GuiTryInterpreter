package View;

import Controller.Controller;
import javafx.stage.Stage;

public abstract class MyScene {
    Stage mainStage;
    Controller controller;
    public MyScene(Stage application, Controller controller) {
        this.controller = controller;
        this.mainStage = application;
    }

    abstract void updateMainStage();
}
