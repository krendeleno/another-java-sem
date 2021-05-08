package mandelbrotexamples;

public interface MandelbrotFractal {
    /**
     * Выдает число от 0 до 1 для каждой точки плоскости, чтобы можно было ее раскрасить
     * @param x координата по x (вещественная часть)
     * @param y координата по y (мнимая часть)
     * @return число от 0 до 1 (число итераций)
     */
    double steps(double x, double y);
}
