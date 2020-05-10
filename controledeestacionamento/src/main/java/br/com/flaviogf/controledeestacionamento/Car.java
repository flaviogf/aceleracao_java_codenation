package br.com.flaviogf.controledeestacionamento;

public class Car {
    private final Driver driver;
    private final String plate;
    private final Color color;

    public Car(Driver driver, String plate, Color color) {
        if (plate == null) {
            throw new NullPointerException();
        }

        if (color == null) {
            throw new NullPointerException();
        }

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

    public Color getColor() {
        return color;
    }

    public boolean isAutonomous() {
        return driver == null;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean hasDriverUnderTheAge() {
        return driver.isUnderTheAge();
    }

    public boolean hasDriverWithoutScore() {
        return driver.isWithoutScore();
    }

    public boolean hasNotSeniorDriver() {
        return driver.isNotSenior();
    }

    public static class Builder {
        private Driver driver;
        private String plate;
        private Color color;

        public Builder driver(Driver value) {
            this.driver = value;
            return this;
        }

        public Builder plate(String value) {
            this.plate = value;
            return this;
        }

        public Builder color(Color value) {
            this.color = value;
            return this;
        }

        public Car build() {
            return new Car(driver, plate, color);
        }
    }
}
