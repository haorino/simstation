package simstation;

import mvc.*;
import java.awt.*;
public abstract class Agent extends Bean implements Runnable {
    protected String name;
    public Heading heading;
    public int xc;
    public int yc;
    protected boolean suspended;
    protected boolean stopped;
    protected Thread myThread;
    protected Color agentColor;

    public Agent() {
        suspended = false;
        stopped = false;
        myThread = null;
        xc = Utilities.rng.nextInt();
        yc = Utilities.rng.nextInt();
    }

    public void run() {
        ;
    }

    public void start() {
        ;
    }

    public synchronized void suspend() { suspended = true; }

    public synchronized void stop() { stopped = true; }

    public synchronized void resume() { notify(); }

    public int getX() {
        return xc;
    }

    public int getY() {
        return yc;
    }

    public void move(int steps) {
        ;
    }

    public void setAgentColor(Color agentColor) {
        this.agentColor = agentColor;
    }
    public abstract void update() ;

}
