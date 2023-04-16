package simstation;
import mvc.Utilities;

public enum Heading {
    NORTH,
    SOUTH,
    EAST,
    WEST;
    public static Heading random(){
        int direction = Utilities.rng.nextInt(4);
        if (direction == 1){
            return NORTH;
        }
        else if (direction == 2) {
            return SOUTH;
        }
        else if (direction == 3) {
            return EAST;
        }
        else {
            return WEST;
        }
    }

    public static Heading parse(String heading) {
        if(heading.equalsIgnoreCase("north")) {
            return NORTH;
        }
        else if(heading.equalsIgnoreCase("south")) {
            return SOUTH;
        }
        else if (heading.equalsIgnoreCase("east")) {
            return EAST;
        }
        else if(heading.equalsIgnoreCase("west")) {
            return WEST;
        }
        else {
            return null;
        }
    }
}
