package boid;

import java.util.List;
import java.util.Random;

import util.Area;
import util.Vector;

public class RandBoid extends Boid {

    private static final double max_speed = 1;
    private static final Random rand = new Random();


    public RandBoid(double x, double y) {
        this(new Vector(x, y));
    }

    public RandBoid(Vector pos) {
        super(pos);
        speed = new Vector(1, 0);
    }

    @Override
    public void update(Area area, List<Boid> neighbours) {
        double angle = rand.nextGaussian()* 0.01;
        speed = speed.rotate(angle);
        pos = pos.translate(speed);
        if(!area.isInside(pos))
            speed.rotate(Math.PI);
    }
    
}
