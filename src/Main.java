package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.*;

import java.util.Optional;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Pane root = new Pane();

        Scene scene = new Scene(root, 1024, 768);
// This is the code that starts up the home page, equipped with a listview that would display the high scores from "HighScore.txt"
        stage.setTitle("Quiz home page");
        stage.setScene(scene);
        stage.show();
// The button here was made in order to start up the selection process in the 'Play a quiz function'
        Button myButton = new Button("Play a Quiz Now!");
        myButton.setLayoutX(200);
        myButton.setLayoutY(100);
        myButton.setPrefSize(200, 35);
        myButton.setOnAction((ActionEvent ae) -> openNewStage(root));
        root.getChildren().add(myButton);
// This listview was made with the main purpose of displaying the high scores on the home page
        ListView<String> list = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList("HighScores");
        list.setItems(items);
        list.setLayoutX(800);
        list.setLayoutY(50);
        list.setPrefSize(200, 400);
        root.getChildren().add(list);


        stage.setOnCloseRequest((WindowEvent we) -> exitPrompt(we));
    }
    // This is the start-up sequence of coding that would produce the second scene
    public static void openNewStage(Pane parent) {
        StageTwo newStage = new StageTwo(parent);
    }

    public static void exitPrompt(WindowEvent we) {
// This is the exit confirmation code that would exit the program upon pressing the 'yes' button
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quit 'Quizmaker'?");
        alert.setHeaderText("Are you sure you want to exit?");

        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        } else {
            we.consume();
        }

    }
    // This was the working class that controls the second scene
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
// This button was made to start the quiz
            Button my2Button = new Button("Start.");
            my2Button.setLayoutX(800);
            my2Button.setLayoutY(710);
            my2Button.setPrefSize(200, 35);
            my2Button.setOnAction((ActionEvent ae) -> openNewStage(root));
            root.getChildren().add(my2Button);
// This is the coding that controlled the combobox, this held the different quiz types and was mapped to the appropriate txt files
            ObservableList Quizzes = FXCollections.observableArrayList("Quiz 1", "Quiz 2", "Quiz 3", "Quiz 4", "Quiz 5");
            ComboBox comboBox = new ComboBox(Quizzes);
            comboBox.setLayoutX(100);
            comboBox.setLayoutY(100);
            comboBox.getSelectionModel().select(0);

            root.getChildren().add(comboBox);
        }

