import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class Main extends Application {

    private static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT = 700;
    private static final int TEXT_SIZE = 20;
    private static final int NUM_TEXTS = 10;
    private static final int TEXT_MOVEMENT_DISTANCE = 5;

    private Random random = new Random();
    private int currentTextIndex = 0;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();

        // Create an array of texts with random content
        Text[] texts = new Text[NUM_TEXTS];
        for (int i = 0; i < NUM_TEXTS; i++) {
            texts[i] = createRandomText();
            root.getChildren().add(texts[i]);
        }

        // Create a timeline for the animation
        Timeline timeline = new Timeline();
        for (Text text : texts) {
            KeyFrame keyFrame = new KeyFrame(
                    Duration.millis(50),
                    event -> moveText(text)
            );
            timeline.getKeyFrames().add(keyFrame);
        }
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setOnFinished(event -> {
            for (Text text : texts) {
                resetTextPosition(text);
            }
        });
        timeline.play();

        // Set up the scene
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sequential Text Motion");
        primaryStage.show();
    }

    private Text createRandomText() {
        Text text = new Text(random.nextInt(WINDOW_WIDTH), random.nextInt(WINDOW_HEIGHT), generateRandomString());
        text.setFont(new javafx.scene.text.Font(TEXT_SIZE));
        return text;
    }

    private String generateRandomString() {
        // Generate a random string for the text content (for demonstration purposes)
        StringBuilder randomString = new StringBuilder();
        int length = random.nextInt(10) + 5; // Random length between 5 and 15 characters
        for (int i = 0; i < length; i++) {
            char randomChar = (char) (random.nextInt(26) + 'A'); // Random uppercase letter
            randomString.append(randomChar);
        }
        return randomString.toString();
    }

    private void moveText(Text text) {
        // Move the text by updating its Y property
        text.setY(text.getY() - TEXT_MOVEMENT_DISTANCE);
    }

    private void resetTextPosition(Text text) {
        // Reset the text position when it goes out of the window bounds
        if (text.getY() + text.getBoundsInLocal().getHeight() < 0) {
            text.setY(WINDOW_HEIGHT);
            text.setX(random.nextInt(WINDOW_WIDTH));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
