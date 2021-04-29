package fractalsexamples;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FractalCirclesDisplay extends Application {

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Мои любимые раскрашенные круги");

        Parent ui = createInterface();
        primaryStage.setScene(new Scene(ui));

        primaryStage.show();
//        initInteraction();

    }

    private void initInteraction() {

    }

    private Parent createInterface() {
        int w = 640;
        int h = 640;
        double x0 = -1.5;
        double y0 = 3.5;
        double d = 0.5;

        WritableImage fractalCircleImage = new WritableImage(w, h);
        ImageView fractalCircleView = new ImageView(fractalCircleImage);
        PixelWriter fractalWriter = fractalCircleImage.getPixelWriter();

        FractalSimpleCircle fractalSimpleCircle = new FractalSimpleCircle();
        PaletteSimpleCircle paletteSimpleCircle = new PaletteSimpleCircle();
//        FractalGrayCircle fractalGrayCircle = new FractalGrayCircle();
//        PaletteGrayCircle paletteGrayCircle = new PaletteGrayCircle();

        for (int xScreen = 0; xScreen < w; xScreen++)
            for (int yScreen = 0; yScreen < h; yScreen++) {
                double x = x0 + d * xScreen;
                double y = y0 + d * yScreen;
                double v = fractalSimpleCircle.evaluate(x,y);
                Color color = paletteSimpleCircle.colorize(v);
//                String colorInt = String.valueOf(paletteSimpleCircle.colorize(v));
//                Color color = Color.web(colorInt);
                fractalWriter.setColor(xScreen, yScreen, color);
            }


        return new Pane(fractalCircleView);
    }
}
