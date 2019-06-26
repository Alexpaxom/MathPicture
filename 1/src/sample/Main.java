package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

    final int WIDTH = 600;
    final int HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Reflection of light rays");
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        final Scene scene = new Scene(root,WIDTH, HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
