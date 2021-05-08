package mandelbrotexamples;

import fractalsexamples.FractalGrayCircle2;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MandelbrotDisplay extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Мои любимые фракталы");

        Parent ui = createInterface();
        primaryStage.setScene(new Scene(ui));

        primaryStage.show();

//        initInteraction();
    }

    private Parent createInterface() {
        int w = 500;
        int h = 500;
        double x0 = -2;
        double y0 = 2;
        double d = 4.0 / w;

        WritableImage imageSimpleFractal = new WritableImage(w, h);
        ImageView imageViewSimpleFractal = new ImageView(imageSimpleFractal);
        PixelWriter simpleFractalPixelWriter = imageSimpleFractal.getPixelWriter();

        IterationNumber iterationNumber = new IterationNumber();
        MandelbrotPaletteSimple mandelbrotPaletteSimple = new MandelbrotPaletteSimple();

        for (int xScreen = 0; xScreen < w; xScreen++)
            for (int yScreen = 0; yScreen < h; yScreen++) {
                double x = x0 + d * xScreen;
                double y = y0 - d * yScreen;
                double v = iterationNumber.steps(x,y);
                Color color = mandelbrotPaletteSimple.colorize(v);
                simpleFractalPixelWriter.setColor(xScreen, yScreen, color);
            }

        WritableImage imageGrayscaleFractal = new WritableImage(w, h);
        ImageView imageViewGrayscaleFractal = new ImageView(imageGrayscaleFractal);
        PixelWriter fractalGrayscaleWriter = imageGrayscaleFractal.getPixelWriter();

        MandelbrotPaletteGrayscale mandelbrotPaletteGrayscale = new MandelbrotPaletteGrayscale();

        for (int xScreen = 0; xScreen < w; xScreen++)
            for (int yScreen = 0; yScreen < h; yScreen++) {
                double x = x0 + d * xScreen;
                double y = y0 - d * yScreen;
                double v = iterationNumber.steps(x,y);
                Color color = mandelbrotPaletteGrayscale.colorize(v);
                fractalGrayscaleWriter.setColor(xScreen, yScreen, color);
            }


        WritableImage imageHSBFractal = new WritableImage(w, h);
        ImageView imageViewHSBFractal = new ImageView(imageHSBFractal);
        PixelWriter fractalHSBWriter = imageHSBFractal.getPixelWriter();

        MandelbrotPaletteHSB mandelbrotPaletteHSB = new MandelbrotPaletteHSB();

        for (int xScreen = 0; xScreen < w; xScreen++)
            for (int yScreen = 0; yScreen < h; yScreen++) {
                double x = x0 + d * xScreen;
                double y = y0 - d * yScreen;
                double v = iterationNumber.steps(x,y);
                Color color = mandelbrotPaletteHSB.colorize(v);
                fractalHSBWriter.setColor(xScreen, yScreen, color);
            }

        return new HBox(imageViewSimpleFractal, imageViewGrayscaleFractal, imageViewHSBFractal);
    }
}
