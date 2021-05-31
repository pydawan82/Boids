package ui;

import java.util.List;

import boid.Boid;
import boid.World;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import util.Animator;
import util.Area;
import util.Vector;

public class ButtonController {

    public World<? extends Boid> world;
    
    public Animator anim = new Animator(30, () -> {
        world.update();
        display();
    });

    @FXML
    private Canvas canvas;
    @FXML
    private Slider speed_slider;
    @FXML
    private Button play_btn;

    public void initialize() {
        System.out.println("Init");
        anim.start();
        anim.setTPS(speed_slider.getValue());
        speed_slider.valueProperty().addListener((o, old, value) -> anim.setTPS(value.doubleValue()));
    }

    public void play() {
        anim.flip();
    }

    public void display() {
        GraphicsContext ctx = canvas.getGraphicsContext2D();
        
        ctx.setFill(Color.WHITE);
        ctx.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        drawArea(ctx);

        for(Boid boid: world.getBoids())
            drawBoid(ctx, boid);
    }

    private void drawArea(GraphicsContext ctx) {
        ctx.save();
        double size = Math.min(canvas.getHeight(), canvas.getWidth());

        ctx.setFill(Color.BLACK);
        ctx.setLineWidth(3);
        ctx.strokeRect(0, 0, size, size);

        ctx.restore();
    }

    private List<Vector> getShape() {
        return List.of(new Vector(-2, .5), new Vector(0, 0), new Vector(-2, -.5));
    }

    private void drawBoid(GraphicsContext ctx, Boid boid) {
        ctx.save();

        ctx.setFill(Color.RED);
        Area area = world.getArea();
        double dx = boid.pos.x / area.width();
        double dy = boid.pos.y / area.height();

        double scale = 1/30d;

        List<Vector> shape = getShape();
        double[] px = new double[shape.size()];
        double[] py = new double[shape.size()];

        int i=0;

        for(Vector point: shape) {
            px[i] = scale*point.x;
            py[i] = scale*point.y;
            i++;
        }

        double size = Math.min(canvas.getHeight(), canvas.getWidth());

        ctx.scale(size, size);
        ctx.translate(dx, dy);
        ctx.rotate(Math.toDegrees(boid.speed.direction()));
        
        ctx.fillPolygon(px, py, shape.size());

        ctx.restore();
    }
}
