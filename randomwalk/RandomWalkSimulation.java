package randomWalks;

import mvc.*;
import simstation.*;

class Drunk extends Agent {

    public Drunk() {
        super();
        heading = Heading.random();
    }

    public void update() {
        heading = Heading.random();
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);
    }
}


class RandomWalkFactory extends SimulationFactory {
    public Model makeModel() {
        return new randomWalkSimulation();
    }
    public String getTitle() { return "Random Walks";}
}

public class randomWalkSimulation extends Simulation {
    public void populate() {
        for(int i = 0; i < 15; i++)
            addAgent(new Drunk());
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new RandomWalkFactory());
        panel.display();
    }

}
