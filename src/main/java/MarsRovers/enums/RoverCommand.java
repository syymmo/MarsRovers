package MarsRovers.enums;

public enum RoverCommand {
    L,
    R,
    M;

    public static boolean contains(String cmd){
        for (RoverCommand command : RoverCommand.values()) {
            if (command.name().equalsIgnoreCase(cmd)){
                return true;
            }
        }
        return false;
    }
}
