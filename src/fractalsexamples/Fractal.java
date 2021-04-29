package fractalsexamples;

public interface Fractal {

    /**
     * Выдает число от 0 до 1 для каждой точки плоскости, чтобы можно было ее раскрасить
     * @param x координата по x
     * @param y координата по y
     * @return число от 0 до 1
     */
    double evaluate(double x, double y);
}
