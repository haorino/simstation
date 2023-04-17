package prisonersDilemma;

import mvc.*;
import simstation.*;

import java.io.Serializable;

interface Strategy extends Serializable {
    boolean cooperate(Prisoner p);
}

class Cooperate implements Strategy {
    public boolean cooperate(Prisoner p) {
        return true;
    }
}
class RandomlyCooperate implements Strategy {
    public boolean cooperate(Prisoner p) {
        if(Math.random()<0.5)
            return true;
        else
            return false;
    }
}
class Cheat implements Strategy {
    public boolean cooperate(Prisoner p) {
        return false;
    }
}
class Tit4Tat implements Strategy {
    public boolean cooperate(Prisoner p) {
        if(p.cheated)
            return false;
        else
            return true;
    }
}

class Prisoner extends Agent {
    Strategy strategy;
    int fitness;
    int type;
    boolean cheated;
    public Prisoner() {
        super();
        fitness = 0;
        cheated = false;
        heading = Heading.random();
    }

    public void cooperate(Prisoner p) {
        if(strategy.cooperate(this) && strategy.cooperate(p)) {
            this.updateFitness(3);
            this.cheated = false;
            p.updateFitness(3);
            p.cheated = false;
        }
        else if(strategy.cooperate(this) && !strategy.cooperate(p)) {
            p.updateFitness(5);
            p.cheated = false;
            this.cheated = true;
        }
        else if(!strategy.cooperate(this) && strategy.cooperate(p)) {
            this.updateFitness(5);
            this.cheated = false;
            p.cheated = true;
        }
        else {
            this.updateFitness(1);
            this.cheated = true;
            p.updateFitness(1);
            p.cheated = true;
        }
    }

    public synchronized void updateFitness(int point) {
        this.fitness += point;
    }

    public void update() {
        Prisoner neighbor = (Prisoner)world.getNeighbor(this, 10);
        if(neighbor != null) {
            neighbor.cooperate(this);
        }
        heading = Heading.random();
        int speed = Utilities.rng.nextInt(10) + 1;
        move(speed);
    }
}

class PrisonerDilemmaFactory extends SimulationFactory {
    public Model makeModel() { return new PrisonerDilemmaSimulation(); }
    public String getTitle() { return "Prisoner Dilemma";}
}

public class PrisonerDilemmaSimulation extends Simulation {
    public void populate() {
        for(int i = 0; i < 10; i++){
            Prisoner p = new Prisoner();
            p.strategy = new Cooperate();
            p.type = 0;
            addAgent(p);
        }

        for(int i = 0; i < 10; i++){
            Prisoner p = new Prisoner();
            p.strategy = new RandomlyCooperate();
            p.type = 1;
            addAgent(p);
        }

        for(int i = 0; i < 10; i++){
            Prisoner p = new Prisoner();
            p.strategy = new Cheat();
            p.type = 2;
            addAgent(p);
        }

        for(int i = 0; i < 10; i++){
            Prisoner p = new Prisoner();
            p.strategy = new Tit4Tat();
            p.type = 3;
            addAgent(p);
        }
    }

    public String[] getStats(){
        int[] average = new int[]{0, 0, 0, 0};
        for(Agent a : agents) {
            Prisoner p = (Prisoner) a;
            average[p.type] += p.fitness;
        }

        String[] stats = new String[5];
        stats[0] = "clock = " + this.getClock();
        stats[1] = "Cooperate Average Fitness= " + average[0] / 10;
        stats[2] = "RandomCooperate Average Fitness= " + average[1] / 10;
        stats[3] = "Cheat Average Fitness= " + average[2] / 10;
        stats[4] = "Tit4Tat Average Fitness= " + average[3] / 10;
        return stats;
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new PrisonerDilemmaFactory());
        panel.display();
    }

}
