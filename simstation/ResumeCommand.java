package simstation;

import mvc.*;

public class ResumeCommand extends Command {
    public ResumeCommand(Model model){
        super(model);
    }
   
    public void execute() {
        Simulation s = (Simulation) model;

        if(s.getAgentsSuspended()){
            s.resume();
            s.changed();
        }
    }
}
