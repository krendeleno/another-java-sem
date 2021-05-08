package mandelbrotexamples;

import javafx.scene.paint.Color;

public class MandelbrotPaletteSimple implements MandelbrotPalette{
    @Override
    public Color colorize(double v) {
        return Color.gray(v);
    }
}
