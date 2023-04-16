package simstation;

import mvc.*;

public class StatsCommand extends Command {
    public StatsCommand(Model model){
        super(model);
    }

    public void execute(){
        Simulation simulation = (Simulation) model;
        String[] stats = simulation.getStats();
        String message ="";
        for(String s : stats) {
            message += s + "\n";
        }
        mvc.Utilities.inform(message);
    }
}
