package br.com.flaviogf.controledeestacionamento;

public class Car {
    private final Driver driver;
    private final String plate;
    private final String color;

    public Car(Driver driver, String plate, String color) {
        this.driver = driver;
        this.plate = plate;
        this.color = color;
    }

    public Driver getDriver() {
        return driver;
    }

    public String getPlate() {
        return plate;
    }

    public String getColor() {
        return color;
    }
}
