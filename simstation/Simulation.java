package simstation;
import mvc.*;
import java.util.*;

public class Simulation extends Model {
    protected int clock = 0;
    protected List<Agent> agents;

    public Simulation() {
        agents = new LinkedList<>();
        clock = 0;
    }
    public void start() {
        ;
    }

    public synchronized void suspend() {
        ;
    }

    public synchronized void resume() {
        ;
    }

    public synchronized void stop() {
        ;
    }

    public synchronized Agent getNeighbor(Agent asker, double radius) {
        return null;
    }

    public void populate() {
        ;
    }

    public List<Agent> getAgents() {
        return agents;
    }
}
