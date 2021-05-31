package util;

public class Vector {
    public final double x,y;

    public Vector() {
        this(0, 0);
    }

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double direction() {
        return Math.atan(y/x) - (x<0 ? Math.PI : 0);
    }

    public Vector translate(Vector d) {
        return new Vector(x+d.x, y+d.y);
    }

    public Vector translate(double dx, double dy) {
        return new Vector(x+dx, y+dy);
    }

    /**
     * 
     * @param angle - rotation angle in radians
     */
    public Vector rotate(double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        return new Vector(x*cos - y*sin, x*sin + y*cos);
    }

    public Vector scale(double f) {
        return scale(f, f);
    }

    public Vector scale(double xf, double yf) {
        return new Vector(x*xf, y*yf);
    }

    public double sqDistance(Vector other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;

        return dx*dx + dy*dy;
    }

    public double distance(Vector other) {
        return Math.sqrt(sqDistance(other));
    }

    public double norm() {
        return Math.sqrt(x*x + y*y);
    }
}
