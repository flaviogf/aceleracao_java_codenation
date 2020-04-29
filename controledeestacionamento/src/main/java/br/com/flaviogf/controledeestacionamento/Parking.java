package br.com.flaviogf.controledeestacionamento;

public class Parking {
    public void park(Car car) {
        if (car.isAutonomous()) {
            throw new ParkingException("Autonomous cars are not allowed");
        }
    }

    public int parkedCars() {
        return 0;
    }

    public boolean isParked(Car car) {
        return true;
    }
}
