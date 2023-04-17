package simstation;
import mvc.*;
import java.util.*;

public class Simulation extends Model {
    protected int clock;
    protected List<Agent> agents;
    public static int SIZE = 250;
    private transient Timer timer;

    public Simulation() {
        agents = new LinkedList<>();
        clock = 0;
    }
    public void start() {
        agents = new LinkedList<>();
        clock = 0;
        populate();
        startTimer();
        for(Agent agent: agents) {
            Thread thread = new Thread(agent);
            thread.start();
        }
    }

    public synchronized void suspend() {
        for(Agent i: agents) {
            i.suspend();
        }
        stopTimer();
    }

    public synchronized void resume() {
        startTimer();
        for(Agent i: agents) {
            i.resume();
        }
    }

    public synchronized void stop() {
        stopTimer();
        for(Agent i: agents) {
            i.stop();
        }
    }

    public synchronized Agent getNeighbor(Agent asker, double radius) {
        int xc = asker.xc;
        int yc = asker.yc;

        ArrayList<Agent> nearby = new ArrayList<>();
        for(Agent a: agents){
            if(a != asker && distance(xc,a.xc,yc,a.yc) < radius){
                nearby.add(a);
            }
        }
        if(nearby.size() == 0){
            return null;
        }
        return nearby.get(Utilities.rng.nextInt(nearby.size()));
    }

    private double distance(int x1,int x2,int y1,int y2){
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public void populate() {}

    public List<Agent> getAgents() {
        return agents;
    }

    public boolean getAgentsSuspended() {
        for(Agent i : agents) {
            return i.suspended;
        }
        return false;
    }
    
    public synchronized void addAgent(Agent a) {
        agents.add(a);
        a.setWorld(this);
    }
    
    public String[] getStats(){
        String[] stats = new String[2];
        stats[0] = "#agents = " + agents.size();
        stats[1] = "clock = " + this.getClock();
        return stats;
    }
    
    public synchronized int getClock() {
        return clock;
    }

    public synchronized void incClock() {
        clock++;
    }
    
    private void startTimer(){
        timer = new Timer();
        timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
    }

    private void stopTimer() {
        timer.cancel();
        timer.purge();
    }

    private class ClockUpdater extends TimerTask {
        public void run(){
            incClock();
        }
    }
}
