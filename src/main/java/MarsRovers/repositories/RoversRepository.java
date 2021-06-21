package MarsRovers.repositories;

import MarsRovers.models.vehicle.Rover;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class RoversRepository implements Repository<Rover>{
    private Map<String, Rover> rovers;

    public RoversRepository() {
        rovers = new LinkedHashMap<>();
    }

    @Override
    public Collection<Rover> getModels() {
        return Collections.unmodifiableCollection(rovers.values());
    }

    @Override
    public void add(Rover rover) {
        if (rover == null){
            throw new NullPointerException("Rover Repository add null rover!");
        }
        rovers.put(rover.getName(), rover);
    }
}
