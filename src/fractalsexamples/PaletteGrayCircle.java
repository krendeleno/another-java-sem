package fractalsexamples;

import javafx.scene.paint.Color;

public class PaletteGrayCircle implements Palette {
    @Override
    public Color colorize(double v) {
        return v == 1 ? Color.WHITE : Color.gray(v);
    };
}
