package MarsRovers.enums;

public enum Orientation {
    N,
    E,
    S,
    W;

    public static boolean validate(String ori){
        for (Orientation orientation : Orientation.values()) {
            if (orientation.name().equalsIgnoreCase(ori)){
                return true;
            }
        }
        return false;
    }
}