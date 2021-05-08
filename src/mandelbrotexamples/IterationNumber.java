package mandelbrotexamples;

public class IterationNumber implements MandelbrotFractal{

    @Override
    public double steps(double x, double y) {
        double zx = 0;
        double zy = 0;
        int R = 10000;
        int N = 1000;

        for (double i = 0; i <= N - 1; i++) {
            double zxNew = zx * zx + x - zy * zy;
            double zyNew = y + 2 * zx * zy;
            zx = zxNew;
            zy = zyNew;
            if ((zx * zx + zy * zy) > R * R)
                return i / N;
        }
        return 1;
    }
}
