package util;

public class Animator {
    
    private long period;
    private Runnable run;

    private volatile boolean paused = true;
    private volatile boolean running = true;
    private Thread t;

    public Animator(double target_tps, Runnable run) {
        setTPS(target_tps);
        this.run = run;

        t = new Thread(this::animate);
    }

    public void start() {
        t.start();
    }

    public void stop() {
        running = false;
    }

    public void flip() {
        paused = !paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public void setTPS(double tps) {
        this.period = (long) (1e9/tps);
    }

    private void animate() {
        try {
            _animate();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void _animate() throws InterruptedException {
        while(running) {
            long start = System.nanoTime();
            if(!paused)
                run.run();
            long end = System.nanoTime();
            long duration = period-(end-start);
            if(duration > 0)
                Thread.sleep(duration/1000000, (int)(duration%1000000));
        }
    }
}
