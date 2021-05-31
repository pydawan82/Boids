package ui;
import java.util.ArrayList;

import boid.Bird;
import boid.RandBoid;
import boid.World;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.Area;
import util.Vector;

public class Boids extends Application {

    private static final String TITLE = "Boids";
    private World<Bird> world;
    private ButtonController controller;

    @Override
    public void start(Stage stage) throws Exception {
        initWorld();
        
        FXMLLoader loader = new FXMLLoader(Boids.class.getResource("template.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        controller.world = world;

        stage.setTitle(TITLE);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        controller.anim.stop();
    }

    private void initWorld() {
        int n = 100;
        Area a = new Area(new Vector(0, 0), new Vector(100, 100));
        ArrayList<Bird> list = new ArrayList<>();
        for(int i=0; i<n; i++) {
            list.add(new Bird(a.randomPoint()));
        }

        world = new World<>(list, a, 100);
    }

}