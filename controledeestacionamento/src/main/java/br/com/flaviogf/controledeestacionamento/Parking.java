package br.com.flaviogf.controledeestacionamento;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class Parking {
    private final Queue<Car> queue = new LinkedList<>();

    public void park(Car car) {
        if (car.isAutonomous()) {
            throw new ParkingException("Autonomous cars are not allowed");
        }

        if (car.hasDriverUnderTheAge()) {
            throw new ParkingException("Driver cannot be under the age");
        }

        if (car.hasDriverWithoutScore()) {
            throw new ParkingException("Driver cannot be without score");
        }

        if (!isOvertake()) {
            queue.add(car);
            return;
        }

        Optional<Car> goingOut = nextToGoOut();

        if (!goingOut.isPresent()) {
            throw new ParkingException("The parking is lot full");
        }

        queue.remove(goingOut.get());

        queue.add(car);
    }

    public int parkedCars() {
        return queue.size();
    }

    public boolean isParked(Car car) {
        return queue.contains(car);
    }

    private boolean isOvertake() {
        return queue.size() >= 10;
    }

    private Optional<Car> nextToGoOut() {
        return queue.stream().filter(Car::hasNotSeniorDriver).findFirst();
    }
}
