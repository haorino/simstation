package simstation;

import mvc.*;
import javax.swing.*;

public class SimulationPanel extends AppPanel {
    public SimulationPanel(AppFactory factory) {
        super(factory);
        JButton start = new JButton("Start");
        start.addActionListener(this);
        super.addControl(start);
        JButton suspend = new JButton("Suspend");
        suspend.addActionListener(this);
        super.addControl(suspend);
        JButton resume = new JButton("Resume");
        resume.addActionListener(this);
        super.addControl(resume);
        JButton stop = new JButton("Stop");
        stop.addActionListener(this);
        super.addControl(stop);
        JButton stats = new JButton("Stats");
        stats.addActionListener(this);
        super.addControl(stats);
    }

    public static void main(String[] args) {
        AppFactory factory = new SimulationFactory();
        AppPanel panel = new SimulationPanel(factory);
        panel.display();
    }
}
