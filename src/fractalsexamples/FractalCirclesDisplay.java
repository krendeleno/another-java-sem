package fractalsexamples;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FractalCirclesDisplay extends Application {
    private Slider sliderHSB;
    private Slider sliderRGB;

    WritableImage imageColorSquare = new WritableImage(256, 256);
    ImageView imageViewColorSquare = new ImageView(imageColorSquare);
    PixelWriter fractalColorWriter = imageColorSquare.getPixelWriter();

    WritableImage imageHSB = new WritableImage(360, 99);
    ImageView imageViewHSB = new ImageView(imageHSB);
    PixelWriter fractalHSBWriter = imageHSB.getPixelWriter();

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Мои любимые раскрашенные круги");

        Parent ui = createInterface();
        primaryStage.setScene(new Scene(ui));

        primaryStage.show();

        initInteraction();

    }

    private void initInteraction() {
        sliderRGB.valueProperty().addListener((value, oldValue, newValue) -> {
            for (int xScreen = 0; xScreen < 256; xScreen++)
                for (int yScreen = 0; yScreen < 256; yScreen++) {
                    fractalColorWriter.setArgb(xScreen, yScreen,
                            (255 << 24) + (xScreen << 16) + (yScreen << 8) + newValue.intValue());
                }
        });

        sliderHSB.valueProperty().addListener((value, oldValue, newValue) -> {
            for (int xScreen = 0; xScreen < 360; xScreen++)
                for (int yScreen = 0; yScreen < 99; yScreen++) {
                    fractalHSBWriter.setColor(xScreen, yScreen,
                            Color.hsb(xScreen, yScreen / 99.0, newValue.doubleValue()));
                }
        });
    }

    private Parent createInterface() {
        int w = 250;
        int h = 250;
        double x0 = -80;
        double y0 = 80;
        double d = 160.0 / w;

        // Простой черный круг
        WritableImage imageSimpleCircle = new WritableImage(w, h);
        ImageView imageViewSimpleCircle = new ImageView(imageSimpleCircle);
        PixelWriter fractalWriterSimpleCircle =  imageSimpleCircle.getPixelWriter();

        FractalSimpleCircle fractalSimpleCircle = new FractalSimpleCircle();
        PaletteSimpleCircle paletteSimpleCircle = new PaletteSimpleCircle();

        for (int xScreen = 0; xScreen < w; xScreen++)
            for (int yScreen = 0; yScreen < h; yScreen++) {
                double x = x0 + d * xScreen;
                double y = y0 - d * yScreen;
                double v = fractalSimpleCircle.evaluate(x,y);
//                Color color = paletteSimpleCircle.colorize(v);
                int color = paletteSimpleCircle.colorize(v);
                fractalWriterSimpleCircle.setArgb(xScreen, yScreen, color);
            }

        // Черный круг с градиентом
        WritableImage imageGrayCircle = new WritableImage(w, h);
        ImageView imageViewGrayCircle = new ImageView(imageGrayCircle);
        PixelWriter fractalGrayWriter = imageGrayCircle.getPixelWriter();

        FractalGrayCircle fractalGrayCircle = new FractalGrayCircle();
        PaletteGrayCircle paletteGrayCircle = new PaletteGrayCircle();

        for (int xScreen = 0; xScreen < w; xScreen++)
            for (int yScreen = 0; yScreen < h; yScreen++) {
                double x = x0 + d * xScreen;
                double y = y0 - d * yScreen;
                double v = fractalGrayCircle.evaluate(x,y);
//                Color color = paletteGrayCircle.colorize(v);
                int color = paletteGrayCircle.colorize(v);
                fractalGrayWriter.setArgb(xScreen, yScreen, color);
            }

        // Еще один черный круг с градиентом
        WritableImage imageGrayCircle2 = new WritableImage(w, h);
        ImageView imageViewGrayCircle2 = new ImageView(imageGrayCircle2);
        PixelWriter fractalGrayWriter2 = imageGrayCircle2.getPixelWriter();

        FractalGrayCircle2 fractalGrayCircle2 = new FractalGrayCircle2();

        for (int xScreen = 0; xScreen < w; xScreen++)
            for (int yScreen = 0; yScreen < h; yScreen++) {
                double x = x0 + d * xScreen;
                double y = y0 - d * yScreen;
                double v = fractalGrayCircle2.evaluate(x,y);
//                Color color = paletteGrayCircle.colorize(v);
                int color = paletteGrayCircle.colorize(v);
                fractalGrayWriter2.setArgb(xScreen, yScreen, color);
            }

        // Цветной квадрат
        for (int xScreen = 0; xScreen < 256; xScreen++)
            for (int yScreen = 0; yScreen < 256; yScreen++) {
                fractalColorWriter.setArgb(xScreen, yScreen,
                        (255 << 24) + (xScreen << 16) + (yScreen << 8) + 0);
            }

        sliderRGB = new Slider(0,255,0);

        // HSB
        for (int xScreen = 0; xScreen < 360; xScreen++)
            for (int yScreen = 0; yScreen < 99; yScreen++) {
                fractalHSBWriter.setColor(xScreen, yScreen,
                        Color.hsb(xScreen, yScreen / 99.0, 1));
            }

        sliderHSB = new Slider(0,1,1);

        return new HBox(imageViewSimpleCircle, imageViewGrayCircle, imageViewGrayCircle2,
                new VBox(imageViewColorSquare, sliderRGB), new VBox(imageViewHSB, sliderHSB));
    }
}
