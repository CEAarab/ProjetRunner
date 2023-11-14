import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GameScene gameScene = new GameScene(new Camera(800, 600));

        // Set up the primary stage
        primaryStage.setTitle("Game App");
        primaryStage.setScene(gameScene);
        primaryStage.show();
        primaryStage.setResizable(false);
        gameScene.render();

    }
}