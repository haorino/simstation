package simstation;

import mvc.*;
import java.awt.*;

public abstract class Agent extends Bean implements Runnable {
    protected String name;
    public Heading heading;
    public int xc;
    public int yc;
    protected Simulation world;
    protected boolean suspended;
    protected boolean stopped;
    protected Thread myThread;
    protected Color agentColor;

    public Agent() {
        suspended = false;
        stopped = false;
        myThread = null;
        xc = Utilities.rng.nextInt(world.SIZE);
        yc = Utilities.rng.nextInt(world.SIZE);
    }

    public void run() {
        myThread = Thread.currentThread();
        while(!stopped) {
            try {
                update();
                Thread.sleep(20);
                checkSuspended();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    public synchronized void suspend() { suspended = true; }
    
    private synchronized void checkSuspended() {
        try {
            while(!stopped && suspended) {
                wait();
                suspended = false;
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public synchronized void stop() { stopped = true; }

    public synchronized void resume() { notify(); }

    public void move(int steps) {
        if(heading == Heading.NORTH){
            yc -= steps;
            if(yc<=0){
                yc += world.SIZE;
            }
        }
        else if(heading == Heading.SOUTH){
            yc += steps;
            if(yc>=world.SIZE){
                yc -= world.SIZE;
            }
        }
        else if(heading == Heading.EAST){
            xc += steps;
            if(xc >= world.SIZE){
                xc -= world.SIZE;
            }
        }
        else if(heading == Heading.WEST){
            xc -= steps;
            if(xc <= 0){
                xc += world.SIZE;
            }
        }
        world.changed();
    }

    public void setAgentColor(Color agentColor) {
        this.agentColor = agentColor;
    }
    
    public void setWorld(Simulation s){
        world = s;
    }
    
    public abstract void update() ;

}
