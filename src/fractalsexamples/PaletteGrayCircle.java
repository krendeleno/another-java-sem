package fractalsexamples;

import javafx.scene.paint.Color;

public class PaletteGrayCircle implements Palette {
    @Override
    public int colorize(double v) {
//        return v == 1 ? Color.WHITE : Color.gray(v);

// Я просто перевожу прозрачность из 0-1 в 0-255, а потом добавляю ее в уже готовый argb
        int opacity = (int) Math.round(v * 255);
        return ((255 - opacity) << 24) + (0 << 16) + (0 << 8) + 0;
    };
}
