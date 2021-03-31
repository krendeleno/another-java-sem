package javafxexamples;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MessengerBoxExample extends Application {
    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Мой любимый мессенджер 1");

        Parent ui = createInterface();
        primaryStage.setScene(new Scene(ui));

        primaryStage.show();
    }

    private Parent createInterface() {
        //Создание необходимых элементов интерфейса
        TextField userInput = new TextField();

        Button send = new Button("Отправить");

        TextArea allMessages = new TextArea();
        HBox userMessage = new HBox(userInput, send);

        VBox leftArea = new VBox(allMessages, userMessage);

        Label labelContacts = new Label("Контакты");
        ListView<String> contacts = new ListView<>();
        VBox rightArea = new VBox(labelContacts, contacts);

        HBox main = new HBox(leftArea, rightArea);

        //Ограничения на размер
        VBox.setVgrow(allMessages, Priority.ALWAYS);
        HBox.setHgrow(userInput, Priority.ALWAYS);
        VBox.setVgrow(contacts, Priority.ALWAYS);
        HBox.setHgrow(leftArea,Priority.ALWAYS);

        contacts.setPrefWidth(200);
        contacts.setMinWidth(200);
        send.setPrefWidth(100);
        send.setMinWidth(100);

        return main;
    }
}
