
package codexbuilder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        // Load the main application view.
        FXMLLoader loader = new FXMLLoader(
                Main.class.getResource("/codexbuilder/view/MainView.fxml"));

        Parent root = loader.load();

        // Configure the application window.
        Scene scene = new Scene(root);

        primaryStage.setTitle("Codex Builder");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(640);
        primaryStage.setMinHeight(400);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
