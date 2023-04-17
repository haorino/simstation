package flocking;

import mvc.*;
import simstation.*;


class Bird extends Agent {
    int speed;

    public Bird() {
        super();
        heading = Heading.random();
        speed = Utilities.rng.nextInt(5)+1;
    }

    public void update() {
        // Find a random neighbor and copy its speed and heading
        Bird neighbor = (Bird) world.getNeighbor(this, 10);

        if(neighbor != null){
            this.heading = neighbor.heading;
            this.speed = neighbor.speed;
        }
        move(speed);
    }
}


class RandomWalkFactory extends SimulationFactory {
    public Model makeModel() { return new flockingSimulation(); }
    public String getTitle() { return "Flocking";}
}

public class flockingSimulation extends Simulation {

    public void populate() {
        for(int i = 0; i < 20; i++)
            addAgent(new Bird());
    }

    @Override
    public String[] getStats(){
        int sp_1 = 0, sp_2 = 0, sp_3 = 0, sp_4 = 0, sp_5 = 0;
        for(Agent a: agents){
            if(((Bird)a).speed == 1) {
                sp_1 +=1;
            }
            else if(((Bird)a).speed == 2) {
                sp_2 +=1;
            }
            else if(((Bird)a).speed == 3) {
                sp_3 +=1;
            }
            else if(((Bird)a).speed == 4) {
                sp_4 +=1;
            }
            else if(((Bird)a).speed == 5) {
                sp_5 +=1;
            }

        }

        String[] stats = new String[5];
        stats[0] = "#bird @ speed 1 = " + sp_1;
        stats[1] = "#bird @ speed 2 = " + sp_2;
        stats[2] = "#bird @ speed 3 = " + sp_3;
        stats[3] = "#bird @ speed 4 = " + sp_4;
        stats[4] = "#bird @ speed 5 = " + sp_5;
        return stats;
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new RandomWalkFactory());
        panel.display();
    }

}
