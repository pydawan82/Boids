package boid;

import java.util.List;

import util.Area;
import util.Vector;

public abstract class Boid {
    
    public Vector pos;
    public Vector speed;

    public Boid(double x, double y) {
        this(new Vector(x, y));
    }

    public Boid(Vector pos) {
        this.pos = pos;
        this.speed = new Vector(1, 0);
    }

    public Vector getPos() {
        return pos;
    }

    public abstract void update(Area area, List<Boid> neighbours);
}
