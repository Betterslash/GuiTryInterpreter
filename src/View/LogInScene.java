package View;
import Controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

import static javafx.scene.paint.Color.DARKGREY;

public class LogInScene extends MyScene{
    GridPane gridPane;
    Scene scene;
    TextField usernameArea;
    Text textUsername;
    Text textPassword;
    Label labelError;
    PasswordField passwordArea;
    Button buttonLogIn;
    Button buttonSingUp;
    Controller controller;
    public LogInScene(Stage application, Controller controller) throws FileNotFoundException {
        super(application, controller);
        labelError = new Label();
        this.usernameArea = new TextField();
        this.textUsername = new Text("Username : ");
        this.passwordArea = new PasswordField();
        this.textPassword = new Text("Password : ");
        this.buttonLogIn = new Button("Login");
        this.buttonSingUp = new Button("Sign Up");
        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setMinSize(620, 470);
        gridPane.setAlignment(Pos.CENTER);
        ImageReader imageReader = new ImageReader("D:\\FirstApplication\\Assets\\logo.png");
        ImageView imageView = imageReader.getImageView();
        imageView.setFitHeight(120);
        imageView.setFitWidth(120);
        gridPane.add(imageView, 2, 0);
        gridPane.add(textUsername, 2, 2);
        gridPane.add(usernameArea, 3 ,2);
        gridPane.add(textPassword, 2, 3);
        gridPane.add(passwordArea, 3, 3);
        gridPane.add(buttonLogIn, 2, 4);
        gridPane.add(labelError, 2, 5);
        gridPane.add(buttonSingUp, 3, 4);
        this.buttonLogIn.setOnAction(e -> {
            FileChecker fileChecker = new FileChecker(usernameArea.getText(), passwordArea.getText());
            try {
                if(fileChecker.check()){
                    this.usernameArea.clear();
                    this.passwordArea.clear();
                    WorkScene workScene = new WorkScene(application, controller);
                    application.setScene(workScene.scene);
                }
                else {
                    this.labelError.setText("Username/Password is incorrect!");
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        this.buttonSingUp.setOnAction(e -> {
            try {
                new FileAdder(this.usernameArea.getText(), this.passwordArea.getText());
                this.usernameArea.clear();
                this.passwordArea.clear();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        gridPane.setBackground(new Background(new BackgroundFill(DARKGREY, CornerRadii.EMPTY, Insets.EMPTY)));
        this.scene = new Scene(this.gridPane);
    }

    public Scene getScene() {
        return scene;
    }

    @Override
    void updateMainStage() {

    }
}
