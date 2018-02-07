package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
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

        public static void openNewStage(Pane parent) {
            StageThree newStage = new StageThree(parent);
        }

        public void closeStage(Stage stage) {

            parent.setDisable(false);
            stage.close();

        }
    }
    // This is the code that produced the third scene
    public static class StageThree {

        static Pane parent;

        public StageThree(Pane theParent) {

            Stage stage = new Stage();
            parent = theParent;
            parent.setDisable(true);
            start(stage);

        }

        public void start(Stage stage) {

            GridPane root = new GridPane();
            Scene scene = new Scene(root, 300, 200);
            stage.setTitle("Have Fun!");
            stage.setScene(scene);
            stage.show();

            String[] answers = {"Question Number:", "Questions answered:"};
            Label[] labels = new Label[2];
            for(int index = 0; index < labels.length; index++) {
                labels[index] = new Label();
            }

            TextField UserAnswer = new TextField();
            UserAnswer.setPromptText("Enter your answer.");
            UserAnswer.setPrefColumnCount(10);
            UserAnswer.getText();
            root.getChildren().add(UserAnswer);
            Button my3button = new Button("Submit");
            GridPane.setConstraints(root, 1, 0);
            root.getChildren().add(my3button);
        }

    }

    //This is the section of coding that would allow the quiz users to read and write to a file, more specifically the high scores file, the rest of the files are read only.
    public static void main(String[] args) {
        launch(args);
        String Filename1 = "./src/HighScore.txt";
        String Filename2 = "./src/Quiz1.txt";
        String Filename3 = "./src/Quiz2.txt";
        String Filename4 = "./src/Quiz3.txt";
        String Filename5 = "./src/Quiz4.txt";
        String Filename6 = "./src/Quiz5.txt";

        String line = null;
//The coding below was all try and catch methods that would read every file and write to the high score file.
        try {
            FileReader fileReader = new FileReader(Filename1);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter(Filename1);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.newLine();
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            bufferedReader.close();
            bufferedWriter.close();

            String text = null;
            int linecount = 0;
            FileReader fileReader1 = new FileReader(Filename2);
            BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
            while ((text = bufferedReader1.readLine()) != null && linecount < 3) {
                System.out.println(text);
                labels[linecount].setText(answers[linecount] + " " + text);
                linecount++;
            }

            bufferedReader1.close();

            FileReader fileReader2 = new FileReader(Filename3);
            BufferedReader bufferedReader2 = new BufferedReader(fileReader2);
            while ((line = bufferedReader2.readLine()) != null) {
                System.out.println(line);
            }

            bufferedReader2.close();

            FileReader fileReader3 = new FileReader(Filename4);
            BufferedReader bufferedReader3 = new BufferedReader(fileReader3);
            while ((line = bufferedReader3.readLine()) != null) {
                System.out.println(line);
            }

            bufferedReader3.close();

            FileReader fileReader4 = new FileReader(Filename5);
            BufferedReader bufferedReader4 = new BufferedReader(fileReader4);
            while ((line = bufferedReader4.readLine()) != null) {
                System.out.println(line);
            }

            bufferedReader4.close();

            FileReader fileReader5 = new FileReader(Filename6);
            BufferedReader bufferedReader5 = new BufferedReader(fileReader5);
            while ((line = bufferedReader5.readLine()) != null) {
                System.out.println(line);
            }
// The exception errors below would be produced if the files were not found or if an IOException was found
            bufferedReader5.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file");
        } catch (IOException ex) {
            System.out.println("Error reading file");
        }
    }
}
