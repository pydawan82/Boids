package boid;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.scene.paint.GradientUtils.Point;

import util.Area;

public class World<B extends Boid> {
    
    private ArrayList<B> boids = new ArrayList<B>();
    private Area area;
    private double range;

    public World(int nb_boids, Class<B> clazz, Area area, double range) {
        this.area = area;
        this.range = range;

        boids.ensureCapacity(nb_boids);

        try {
            Constructor<B> constructor = clazz.getConstructor(Point.class);

            for(int i=0; i<nb_boids; i++) {
                boids.add(constructor.newInstance(area.randomPoint()));
            }
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
    }

    public World(List<B> boids, Area area, double range) {
        this.boids.addAll(boids);
        this.area = area;
        this.range = range;
    }

    public ArrayList<B> getBoids() {
        return boids;
    }

    public Area getArea() {
        return area;
    }

    public double getRange() {
        return range;
    }

    public void update() {
        double sqRange = range*range;
        for(B boid: boids) {
            List<Boid> neighbours = boids.stream()
                    .filter(b -> b!=boid)
                    .filter(b -> boid.getPos().sqDistance(b.getPos()) < sqRange)
                    .map(b -> (Boid) b)
                    .toList();

            boid.update(area, neighbours);
        }
    }

}
