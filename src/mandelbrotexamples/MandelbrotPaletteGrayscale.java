package mandelbrotexamples;

import javafx.scene.paint.Color;

public class MandelbrotPaletteGrayscale implements MandelbrotPalette{
    @Override
    public Color colorize(double v) {
        v = v * 10 - Math.floor(v * 10);
        return Color.gray(v);
    }
}
