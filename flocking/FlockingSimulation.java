package flocking;

import mvc.*;
import simstation.*;


class Bird extends Agent {
    int speed = Utilities.rng.nextInt(3)+1;

    public Bird() {
        super();
        heading = Heading.random();
    }

    public void update() {
        // Find a random neighbor and copy its speed and heading
        Agent neighbor = world.getNeighbor(this, 15);

        if(neighbor == null){
            heading = Heading.random();
            speed = Utilities.rng.nextInt(10) + 1;
        }else{
            Bird b = (Bird)neighbor;
            heading = b.heading;
            speed = b.speed;
        }
        move(speed);
    }
}


class RandomWalkFactory extends SimulationFactory {
    public Model makeModel() {
        return new FlockingSimulation();
    }
    public String getTitle() { return "Random Walks";}
}

public class FlockingSimulation extends Simulation {

    public void populate() {
        for(int i = 0; i < 20; i++)
            addAgent(new Bird());
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new RandomWalkFactory());
        panel.display();
    }

    @Override
    public String[] getStats(){
        int[] speeds = new int[5];
        for(Agent a: agents){
            Bird b = (Bird)a;
            speeds[b.speed]++;
        }

        String[] stats = new String[6];
        for(int i = 0; i < speeds.length; i++){
            stats[i] = "bird at speed " + i + ": " + speeds[i];
        }
        stats[5] = "clock = " + this.getClock();
        return stats;
    }

}
