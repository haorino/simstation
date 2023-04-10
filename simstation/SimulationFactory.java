package simstation;

import mvc.*;

public class SimulationFactory implements AppFactory {
    public Simulation makeModel() {
        return new Simulation();
    }

    public View makeView(Model m){
        return new SimulationView((Simulation) m);
    }

    public String[] getEditCommands() {
        return new String[]{"Start", "Suspend", "Resume", "Stop", "Stats"};
    }

    public Command makeEditCommand(Model model, String type, Object source) {
        if (type == "Start")
            return new StartCommand(model);
        else if(type == "Suspend")
            return new SuspendCommand(model);
        else if(type == "Resume")
            return new ResumeCommand(model);
        else if(type == "Stop")
            return new StopCommand(model);
        else if(type == "Stats")
            return new StatsCommand(model);
        else
            return null;
    }

    public String getTitle() { return "Simstation"; }

    public String[] getHelp() {
        return new String[] {""};
    }

    public String about() {
        return "Simstation Version 1.0";
    }
}
