package sample;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Animation.Status;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import java.lang.Math;
import javafx.scene.control.Spinner;

public class Controller {
    @FXML
    private Canvas canvas;
    @FXML
    private Button btn;
    @FXML
    NumberTextField step_number;
    @FXML
    Spinner delay;
    @FXML
    NumberTextField count_pieces;

    final int WIDTH = 600;
    final int HEIGHT = 600;
    final int CIRCLE_RADIUS = 250;
    int COUNT_PIECES = 400;
    int multiple_num = 1;
    Timeline animation;

    GraphicsContext gc;

    @FXML
    public void initialize() {
        canvas.setWidth(WIDTH);
        canvas.setHeight(HEIGHT);

        gc = canvas.getGraphicsContext2D();
        animation = new Timeline(new KeyFrame(Duration.seconds(1), animateFunc()));
        animation.setCycleCount(Timeline.INDEFINITE);//Set to run Timeline forever or as long as the app is running.
    }

    @FXML
    private void click(ActionEvent event) {
        multiple_num = Integer.parseInt(step_number.getText());
        COUNT_PIECES = Integer.parseInt(count_pieces.getText());

        if(animation.getStatus() == Status.STOPPED || animation.getStatus() == Status.PAUSED) {
            btn.setText("Stop");
            animation = new Timeline(new KeyFrame(Duration.seconds((Double) delay.getValue()), animateFunc()));
            animation.setCycleCount(Timeline.INDEFINITE);
            animation.play();
        }
        else {
            btn.setText("Run");
            animation.stop();
        }
    }

    EventHandler<ActionEvent> animateFunc() {

        return (ActionEvent event1) -> {
            gc.clearRect(0, 0, WIDTH, HEIGHT);
            gc.setStroke(Color.rgb((int) (Math.random() * 200), (int) (Math.random() * 200), (int) (Math.random() * 200)));
            gc.strokeOval(WIDTH / 2 - CIRCLE_RADIUS, HEIGHT / 2 - CIRCLE_RADIUS, CIRCLE_RADIUS * 2, CIRCLE_RADIUS * 2);
            for (int i = 0; i <= COUNT_PIECES; ++i) {
                int x1 = (int) (Math.cos((2 * Math.PI) / COUNT_PIECES * i) * CIRCLE_RADIUS) + WIDTH / 2;
                int y1 = (int) (Math.sin((2 * Math.PI) / COUNT_PIECES * i) * CIRCLE_RADIUS) + HEIGHT / 2;

                int pos_in_circle = (i * multiple_num) % COUNT_PIECES;
                int x2 = (int) (Math.cos((2 * Math.PI) / COUNT_PIECES * pos_in_circle) * CIRCLE_RADIUS) + WIDTH / 2;
                int y2 = (int) (Math.sin((2 * Math.PI) / COUNT_PIECES * pos_in_circle) * CIRCLE_RADIUS) + HEIGHT / 2;

                gc.strokeLine(x1, y1, x2, y2);
            }
            step_number.setText(Integer.toString(multiple_num));
            multiple_num += 1;
        };
    }
}
