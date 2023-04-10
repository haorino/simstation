package simstation;
import mvc.*;

import java.awt.*;

public class SimulationView extends View {
    public SimulationView(Simulation s) {
        super(s);
        setBackground(Color.GRAY);
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Simulation simulation = (Simulation) model;
        Color oldColor = gc.getColor();
        for(Agent agent: simulation.getAgents()) {
            gc.setColor(agent.agentColor);
            gc.fillOval(agent.xc, agent.yc, 4, 4);
        }
        gc.setColor(oldColor);
    }
}
