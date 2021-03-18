package javafxexamples;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ButtonExample extends Application {
    private Label l;
    private Button b;
    private int i = 0;
    private VBox vBox;
    private ImageView explosion;

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage.setTitle("Моя любимая кнопка");

        Parent ui = createInterface();
        primaryStage.setScene(new Scene(ui, 640, 480));

        primaryStage.show();
        initInteraction();
    }

    private void counter() {
        i++;
    }

    private void initInteraction() {
        b.setOnAction(actionEvent -> {
            switch (i) {
                case 0 -> l.setText("Не нажимай больше на эту кнопку");
                case 1 -> l.setText("Я просил, не нажимай больше на эту кнопку");
                case 2 -> l.setText("Надеюсь, ты счастлив, теперь хватит");
                case 3 -> l.setText("Мы никогда не сможем быть друзьями");
                case 4 -> l.setText("Еще раз и я устрою суицид");
                case 5 -> {
                    vBox.getChildren().clear();
                    vBox.getChildren().add(explosion);
                }
            }
            counter();
        });
    }

    private Parent createInterface() throws FileNotFoundException {
        b = new Button("Нажми меня!");
        b.setPrefSize(200,48);
        b.setStyle("-fx-background-color: #502570; -fx-text-fill: white; -fx-font-size: 2em");

        l = new Label();

        explosion = new ImageView(new Image(new FileInputStream("explosion.gif")));

        vBox = new VBox(b, l);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }
}