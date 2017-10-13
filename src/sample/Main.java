package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Optional;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Pane root = new Pane();

        Scene scene = new Scene(root, 1024, 768);

        stage.setTitle("Quiz home page");
        stage.setScene(scene);
        stage.show();
        Button myButton = new Button("Proceed!");
        myButton.setPrefSize(100, 35);
        myButton.setOnAction((ActionEvent ae) -> openNewStage(root));
        root.getChildren().add(myButton);
        stage.setOnCloseRequest((WindowEvent we) -> exitPrompt(we));

    }

    public static void openNewStage(Pane parent) {
        StageTwo newStage = new StageTwo(parent);
    }

    public static void exitPrompt(WindowEvent we) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure you want to exit?");

        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            System.exit(0);
        } else {
            we.consume();
        }

    }

    public static class StageTwo {

        static Pane parent;

        public StageTwo(Pane theParent) {

            Stage stage = new Stage();
            parent = theParent;
            parent.setDisable(true);
            start(stage);

        }

        public void start(Stage stage) {

            Pane root = new Pane();
            Scene scene = new Scene(root, 1024, 768);
            stage.setTitle("Choose your Quiz and play.");
            stage.setScene(scene);
            stage.setOnCloseRequest((WindowEvent we) -> closeStage(stage));
            stage.show();
        }

        public void closeStage(Stage stage) {

            parent.setDisable(false);
            stage.close();

        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
