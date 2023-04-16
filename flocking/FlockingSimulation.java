package flocking;

import mvc.*;
import simstation.*;

import java.util.ArrayList;
import java.util.Random;

class Bird extends Agent {
    int speed = Utilities.rng.nextInt(2)+1;

    public Bird() {
        super();
        heading = Heading.random();
    }

    public void update() {
        // Find a random neighbor and copy its speed and heading
        ArrayList<Agent> nearby = new ArrayList<>();
        for(Agent a: world.getAgents()){
            if(distance(xc,a.xc,yc,a.yc) < 15){
                nearby.add(a);
            }
        }
        int steps;
        if(nearby.size() == 0){
            heading = Heading.random();
            speed = Utilities.rng.nextInt(10) + 1;
        }else{
            Random r = new Random();
            Bird neighbor = (Bird)nearby.get(r.nextInt(nearby.size()));
            heading = neighbor.heading;
            speed = neighbor.speed;
        }
        move(speed);
    }

    private double distance(int x1,int x2,int y1,int y2){
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
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

}
