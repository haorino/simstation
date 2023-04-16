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

class PrisonerDilemmaFactory extends SimulationFactory {
    public Model makeModel() { return new PrisonerDilemmaSimulation(); }
    public String getTitle() { return "Prisoner Dilemma";}
}

public class prisonersDilemmaSimulation extends Simulation {
  
  public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new PrisonerDilemmaFactory());
        panel.display();
    }
}
