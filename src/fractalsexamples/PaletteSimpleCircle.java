package fractalsexamples;

import javafx.scene.paint.Color;

public class PaletteSimpleCircle implements Palette {
    @Override
    public int colorize(double v) {
//        return v == 1? Color.WHITE : Color.BLACK;
        return v == 1? 0xFFFFFFFF : 0xFF000000;
    }
}
