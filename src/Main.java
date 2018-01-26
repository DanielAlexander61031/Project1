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


