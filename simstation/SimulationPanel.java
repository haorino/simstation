package simstation;

import mvc.*;
import javax.swing.*;
import java.awt.*;

public class SimulationPanel extends AppPanel {
    public SimulationPanel(AppFactory factory) {
        super(factory);
        controlPanel.setLayout(new GridLayout(5, 1));
        String[] buttons = {"Start", "Suspend", "Resume", "Stop", "Stats"};
        for(String name : buttons) {
            JButton btn = new JButton(name);
            btn.addActionListener(this);
            JPanel btnPanel = new JPanel();
            btnPanel.setLayout(new FlowLayout());
            btnPanel.setBackground(Color.pink);
            btnPanel.add(btn);
            super.addControl(btnPanel);
        }
    }

    public static void main(String[] args) {
        AppFactory factory = new SimulationFactory();
        AppPanel panel = new SimulationPanel(factory);
        panel.display();
    }
}
