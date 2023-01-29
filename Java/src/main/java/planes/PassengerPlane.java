package planes;

import java.util.Objects;

public class PassengerPlane extends Plane{
    private final int passengersCapacity;

    public PassengerPlane(PlaneProperties planeProperties, int passengersCapacity) {
        super(planeProperties);
        this.passengersCapacity = passengersCapacity;
    }

    public int getPassengersCapacity() {
        return passengersCapacity;
    }

    @Override
    public String toString() {
        return super.toString().replace("}",
                ", passengersCapacity=" + passengersCapacity +
                '}');
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PassengerPlane)) return false;
        if (!super.equals(o)) return false;
        return passengersCapacity == ((PassengerPlane) o).passengersCapacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), passengersCapacity);
    }
}
