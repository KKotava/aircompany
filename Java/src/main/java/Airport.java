import planes.ExperimentalPlane;
import models.MilitaryType;
import planes.MilitaryPlane;
import planes.PassengerPlane;
import planes.Plane;

import java.util.*;
import java.util.stream.Collectors;

public class Airport {
    private List<? extends Plane> planes;

    public Airport(List<? extends Plane> planes) {
        this.planes = planes;
    }

    public List<PassengerPlane> getPassengerPlanes() {
        return getPlanesOfType(PassengerPlane.class);
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        return getPlanesOfType(MilitaryPlane.class);
    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        return getPlanesOfType(ExperimentalPlane.class);
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        return getPassengerPlanes().stream()
                .max(Comparator.comparing(PassengerPlane::getPassengersCapacity))
                .orElseThrow(NoSuchElementException::new);
    }

    public List<MilitaryPlane> getTransportMilitaryPlanes() {
        return getMilitaryPlanesOfType(MilitaryType.TRANSPORT.name());
    }

    public List<MilitaryPlane> getBomberMilitaryPlanes() {
        return  getMilitaryPlanesOfType(MilitaryType.BOMBER.name());
    }

    private List<MilitaryPlane> getMilitaryPlanesOfType (String type) {
        return getMilitaryPlanes().stream()
                .filter(plane -> plane.getType().equals(MilitaryType.valueOf(type)))
                .collect(Collectors.toList());
    }

    public Airport sortByMaxDistance() {
        sortWithComparator(Comparator.comparingInt(Plane::getMaxFlightDistance));
       return this;
    }

    public Airport sortByMaxSpeed() {
        sortWithComparator(Comparator.comparingInt(Plane::getMaxSpeed));
        return this;
    }

    public void sortByMaxLoadCapacity() {
        sortWithComparator(Comparator.comparingInt(Plane::getMaxLoadCapacity));
    }
    private void sortWithComparator(Comparator comparator) {
        planes = (List<? extends Plane>) planes.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    private <T> List<T> getPlanesOfType(Class<T> planeType) {
        return planes.stream()
                .filter(plane -> plane.getClass().equals(planeType))
                .map(plane -> (T) plane)
                .collect(Collectors.toList());
    }

    public List<? extends Plane> getPlanes() {
        return planes;
    }

    @Override
    public String toString() {
        return "Airport{" + "planes=" + planes.toString() + "}";
    }

}
