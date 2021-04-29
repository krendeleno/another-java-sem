package fractalsexamples;

public class FractalSimpleCircle implements Fractal {

    @Override
    public double evaluate(double x, double y) {

        double r = Math.pow(x - 100, 2) + Math.pow(y - 100, 2);
        return r > 6400? 1 : 0;
    }
}
