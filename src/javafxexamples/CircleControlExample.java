package javafxexamples;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CircleControlExample extends Application {
    private Slider slider;
    private Circle circle;
    private ColorPicker circleColor;
    private ColorPicker backgroundColor;
    private Pane right;

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Мой любимый круг");

        Parent ui = createInterface();
        primaryStage.setScene(new Scene(ui, 640, 480));

        primaryStage.show();
        initInteraction();

    }

    private void initInteraction() {

        System.out.println(slider.getValue());
        circle.radiusProperty().bind(slider.valueProperty());
        circle.centerXProperty().bind(Bindings.createDoubleBinding(
                () -> right.getWidth()/2,right.widthProperty()
        ));
        circle.centerYProperty().bind(Bindings.createDoubleBinding(
                () -> right.getHeight()/2,right.heightProperty()
        ));
        circle.fillProperty().bind(circleColor.valueProperty());

        backgroundColor.valueProperty().addListener((value, oldValue, newValue) -> {
            right.setStyle("-fx-background-color: #" + newValue.toString().substring(2,8));
            });

        slider.maxProperty().bind(Bindings.createDoubleBinding(
                () -> Math.min(right.getHeight()/2,right.getWidth()/2),
                right.heightProperty(),right.widthProperty()
        ));
    }

    private Parent createInterface() {
        //Необходимые элементы
        Label labelSlider = new Label("Радиус");
        Label labelCircleColor = new Label("Цвет круга");
        Label labelBackgroundColor = new Label("Цвет фона");

        circle = new Circle();
        right = new Pane(circle);
        slider = new Slider(0,Math.min(right.getWidth(),right.getHeight()),0);
        circleColor = new ColorPicker();
        backgroundColor = new ColorPicker();

        VBox left = new VBox(labelSlider, slider, labelCircleColor, circleColor,
                labelBackgroundColor, backgroundColor);

        HBox main = new HBox(left, right);

        //Ограничения
        HBox.setHgrow(right, Priority.ALWAYS);
        VBox.setVgrow(left, Priority.ALWAYS);
        left.setPrefWidth(200);
        left.setMinWidth(200);

        //Граница, чтобы понимать че происходит
        left.setStyle("-fx-border-color: black; -fx-border-radius: 2px");

        //Чтобы бэкграунд сразу соответствовал ColorPicker
        right.setStyle("-fx-background-color: #" + backgroundColor.getValue().toString().substring(2,8));

        return main;
    }
}
