package mandelbrotexamples;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static javafx.scene.input.MouseEvent.MOUSE_PRESSED;
import static javafx.scene.input.MouseEvent.MOUSE_RELEASED;

public class MandelbrotDisplay extends Application {
    int w = 500;
    int h = 500;
    double x0 = -2;
    double y0 = 2;
    double dSimple = 4.0 / w;
    double dGrayscale = 4.0 / w;
    double dHSB = 4.0 / w;

    double x1;
    double y1;
    double d;

    WritableImage imageSimpleFractal = new WritableImage(w, h);
    ImageView imageViewSimpleFractal = new ImageView(imageSimpleFractal);
    PixelWriter simpleFractalPixelWriter = imageSimpleFractal.getPixelWriter();
    IterationNumber iterationNumber = new IterationNumber();
    MandelbrotPaletteSimple mandelbrotPaletteSimple = new MandelbrotPaletteSimple();

    WritableImage imageGrayscaleFractal = new WritableImage(w, h);
    ImageView imageViewGrayscaleFractal = new ImageView(imageGrayscaleFractal);
    PixelWriter fractalGrayscaleWriter = imageGrayscaleFractal.getPixelWriter();
    MandelbrotPaletteGrayscale mandelbrotPaletteGrayscale = new MandelbrotPaletteGrayscale();

    WritableImage imageHSBFractal = new WritableImage(w, h);
    ImageView imageViewHSBFractal = new ImageView(imageHSBFractal);
    PixelWriter fractalHSBWriter = imageHSBFractal.getPixelWriter();
    MandelbrotPaletteHSB mandelbrotPaletteHSB = new MandelbrotPaletteHSB();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Мои любимые фракталы");

        Parent ui = createInterface();
        primaryStage.setScene(new Scene(ui));

        primaryStage.show();

        initInteraction();
    }

    private void sourceD(Event e) {
        if (e.getSource().equals(imageViewSimpleFractal))
            d = dSimple;
        else if (e.getSource().equals(imageViewGrayscaleFractal))
            d = dGrayscale;
        else
            d = dHSB;
    }

    private void initInteraction() {
        EventHandler<ScrollEvent> scaleEventHandler =
                e -> {
                    sourceD(e);
                    if (e.getDeltaY() > 0) {
                        x0 = x0 + e.getX() * d - e.getX() * d / 1.5;
                        y0 = y0 + e.getY() * d - e.getY() * d / 1.5;
                        d = d / 1.5;
                    } else if (e.getDeltaY() < 0) {
                        x0 = x0 + e.getX() * d - e.getX() * d * 1.5;
                        y0 = y0 + e.getY() * d - e.getY() * d * 1.5;
                        d = d * 1.5;
                    }
                    if (e.getSource().equals(imageViewSimpleFractal)) {
                        drawMandelbrot(simpleFractalPixelWriter, mandelbrotPaletteSimple,
                                x0, y0, d);
                        dSimple = d;
                    }
                    if (e.getSource().equals(imageViewGrayscaleFractal)) {
                        drawMandelbrot(fractalGrayscaleWriter, mandelbrotPaletteGrayscale,
                                x0, y0, d);
                        dGrayscale = d;
                    }
                    if (e.getSource().equals(imageViewHSBFractal)) {
                        drawMandelbrot(fractalHSBWriter, mandelbrotPaletteHSB,
                                x0, y0, d);
                        dHSB = d;
                    }
                };

        EventHandler<MouseEvent> moveEventHandler =
                e -> {
                    sourceD(e);
                    if (e.getEventType().equals(MOUSE_PRESSED)) {
                        x1 = e.getX();
                        y1 = e.getY();

                    }
                    if (e.getEventType().equals(MOUSE_RELEASED)){
                        double x2 = e.getX();
                        double y2 = e.getY();

                        x0 = x0 + d * (x2 - x1);
                        y0 = y0 - d * (y2 - y1);

                        if (e.getSource().equals(imageViewSimpleFractal))
                            drawMandelbrot(simpleFractalPixelWriter, mandelbrotPaletteSimple,
                                x0, y0, d);
                        if (e.getSource().equals(imageViewGrayscaleFractal))
                            drawMandelbrot(fractalGrayscaleWriter, mandelbrotPaletteGrayscale,
                                    x0, y0, d);
                        if (e.getSource().equals(imageViewHSBFractal))
                            drawMandelbrot(fractalHSBWriter, mandelbrotPaletteHSB,
                                    x0, y0, d);
                    };
                };
        imageViewSimpleFractal.addEventHandler(MouseEvent.ANY, moveEventHandler);
        imageViewSimpleFractal.addEventHandler(ScrollEvent.ANY, scaleEventHandler);
        imageViewGrayscaleFractal.addEventHandler(MouseEvent.ANY, moveEventHandler);
        imageViewGrayscaleFractal.addEventHandler(ScrollEvent.ANY, scaleEventHandler);
        imageViewHSBFractal.addEventHandler(MouseEvent.ANY, moveEventHandler);
        imageViewHSBFractal.addEventHandler(ScrollEvent.ANY, scaleEventHandler);

    }

    private void drawMandelbrot(PixelWriter pixelWriter, MandelbrotPalette palette,
                                double x0, double y0, double d) {
        for (int xScreen = 0; xScreen < w; xScreen++)
            for (int yScreen = 0; yScreen < h; yScreen++) {
                double x = x0 + d * xScreen;
                double y = y0 - d * yScreen;
                double v = iterationNumber.steps(x,y);
                Color color = palette.colorize(v);
                pixelWriter.setColor(xScreen, yScreen, color);
            }
    }

    private Parent createInterface() {
        drawMandelbrot(simpleFractalPixelWriter, mandelbrotPaletteSimple,
                x0, y0, dSimple);
        drawMandelbrot(fractalGrayscaleWriter, mandelbrotPaletteGrayscale,
                x0, y0, dGrayscale);
        drawMandelbrot(fractalHSBWriter, mandelbrotPaletteHSB,
                x0, y0, dHSB);
        return new HBox(imageViewSimpleFractal, imageViewGrayscaleFractal, imageViewHSBFractal);
    }
}
