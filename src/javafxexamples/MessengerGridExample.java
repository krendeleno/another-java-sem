package javafxexamples;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MessengerGridExample extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Мой любимый мессенджер 2");

        Parent ui = createInterface();
        primaryStage.setScene(new Scene(ui));

        primaryStage.show();
    }

    private Parent createInterface() {
        //Создание необходимых элементов интерфейса
        TextField userInput = new TextField();
        Button send = new Button("Отправить");
        TextArea allMessages = new TextArea();
        Label labelContacts = new Label("Контакты");
        ListView<String> contacts = new ListView<>();

        GridPane main = new GridPane();

        //Добавление элементов на сетку
        main.add(allMessages,0,0,2,2);
        main.add(userInput, 0,2);
        main.add(send, 1,2);
        main.add(labelContacts,2,0);
        main.add(contacts, 2,1,1,2);

        //Ограничения, чтобы это все нормально выглядело
        GridPane.setHalignment(labelContacts, HPos.CENTER);
        send.setMaxHeight(Double.MAX_VALUE);
        send.setMaxWidth(Double.MAX_VALUE);
        userInput.setMaxWidth(Double.MAX_VALUE);
        userInput.setMaxHeight(Double.MAX_VALUE);

        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        ColumnConstraints col3 = new ColumnConstraints();
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        RowConstraints row3 = new RowConstraints();

        col1.setHgrow(Priority.ALWAYS);
        col2.setPrefWidth(150);
        col3.setPrefWidth(200);

        row1.setPrefHeight(40);
        row2.setVgrow(Priority.ALWAYS);
        row3.setMaxHeight(50);

        main.getColumnConstraints().addAll(col1, col2, col3);
        main.getRowConstraints().addAll(row1, row2, row3);

        return main;
    }
}
