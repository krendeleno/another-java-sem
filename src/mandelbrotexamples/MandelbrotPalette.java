package mandelbrotexamples;

import javafx.scene.paint.Color;

public interface MandelbrotPalette {

    /**
     * Определяет цвет для числа от 0 до 1
     * @param v число от 0 до 1
     * @return цвет
     */
    Color colorize (double v);
//    int colorize (double v);
}
