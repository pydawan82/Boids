package boid;

import java.util.List;

import util.Area;
import util.Vector;

public class Bird extends Boid {

    public Bird(double x, double y) {
        this(new Vector(x, y));
    }

    public Bird(Vector pos) {
        super(pos);
    }

    @Override
    public void update(Area area, List<Boid> neighbours) {
        Vector center = neighbours.stream()
                .map(b -> b.pos)
                .reduce(new Vector(), Vector::translate)
                .scale(1d/neighbours.size());
        
        double da = center.direction()-pos.direction();
        if(da < -Math.PI)
            da+=2*Math.PI;

        if(neighbours.size() != 0) {
            double d = pos.distance(center);
            speed = speed.rotate(Math.signum(da)*0.01*d);
        }

        pos = pos.translate(speed);
        if(!area.isInside(pos))
            speed = speed.rotate(Math.PI);
    }
    
}
