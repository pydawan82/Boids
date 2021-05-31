package util;

import java.util.Random;

public class Area {

    private static final Random rand = new Random();

    public Vector p1, p2;

    public Area(Vector p1, Vector p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public double width() {
        return Math.abs(p1.x - p2.x);
    }

    public double height() {
        return Math.abs(p1.y - p2.y);
    }

    private boolean isBetween(double d, double lo, double hi) {
        if(lo > hi) {
            double temp = lo;
            lo = hi;
            hi = temp;
        }

        return d <= hi && d >= lo; 
    }

    public boolean isInside(Vector p) {
        return isBetween(p.x, p1.x, p2.x) && isBetween(p.y, p1.y, p2.y);
    }

    public Vector center() {
        double x = (p1.x+p2.x)/2;
        double y = (p1.y+p2.y)/2;

        return new Vector(x, y);
    }

    public Vector randomPoint() {
        double x = (p2.x-p1.x) * rand.nextDouble() + p1.x;
        double y = (p2.y-p1.y) * rand.nextDouble() + p1.y;

        return new Vector(x, y);
    }


}