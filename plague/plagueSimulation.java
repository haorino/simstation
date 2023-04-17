package plague;
import mvc.*;
import simstation.*;

import java.awt.*;

class Plague extends Agent {
    private boolean infected;
    public int resistance;

    public Plague() {
        super();
        resistance = plagueSimulation.RESISTANCE;
        infected = mvc.Utilities.rng.nextInt(100) < plagueSimulation.PERCENTAGE_INFECTED;
        heading = Heading.random();
    }

    public synchronized boolean isInfected(){ return infected; }

    public void setInfected(boolean infected){
        this.infected = infected;
        this.setAgentColor(Color.red);
    }

    public void update() {
        if(this.isInfected()){
            Plague neighbor = (Plague)world.getNeighbor(this, plagueSimulation.VIRULENCE);
            if(neighbor != null && !neighbor.isInfected()){
                if(resistance < Utilities.rng.nextInt(100)){
                    neighbor.setInfected(true);
                }
            }
        }
        move(2);
    }
}


class PlagueFactory extends SimulationFactory {
    public Model makeModel() { return new plagueSimulation(); }
    public String getTitle() { return "Plague";}
}

public class plagueSimulation extends Simulation {

    public static int VIRULENCE = 10; // % chance of infection
    public static int RESISTANCE = 90; // % chance of resisting infection
    public static int PERCENTAGE_INFECTED = 10;
    public static int POPULATION = 80;

    public void populate() {
        for(int i = 0; i < POPULATION; i++){ //Start half are infected
            Plague newPlague = new Plague();
            if(newPlague.isInfected()){
                newPlague.setAgentColor(Color.red);
            }
            else{
                newPlague.setAgentColor(Color.green);
            }
            addAgent(newPlague);
        }
    }

    public String[] getStats(){
        int infected = 0;
        for(Agent a : agents) {
            Plague p = (Plague) a;
            if(p.isInfected()){
                infected++;
            }
        }
        String[] stats = new String[3];
        stats[0] = "#agents = " + agents.size();
        stats[1] = "clock = " + this.getClock();
        stats[2] = "% infected = " + (infected/(float)agents.size())*100;
        return stats;
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new PlagueFactory());
        panel.display();
    }
}
