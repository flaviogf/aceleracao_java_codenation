package br.com.flaviogf.controledeestacionamento;

import org.junit.Before;
import org.junit.Test;

public class ParkingTest {
    private Parking parking;

    @Before
    public void setUp() {
        parking = new Parking();
    }

    @Test(expected = NullPointerException.class)
    public void when_the_driver_has_not_license_should_throw_an_exception() {
        Driver.builder()
                .name("Ada")
                .age(17)
                .score(10)
                .build();
    }

    @Test(expected = NullPointerException.class)
    public void when_the_driver_has_not_name_should_throw_an_exception() {
        Driver.builder()
                .age(17)
                .score(10)
                .license("12213")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_the_driver_age_is_negative_should_throw_an_exception() {
        Driver.builder().age(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_the_driver_score_is_negative_should_throw_an_exception() {
        Driver.builder().score(-1);
    }

    @Test(expected = NullPointerException.class)
    public void when_the_car_has_not_plate_should_throw_an_exception() {
        Car.builder()
                .color(Color.COLORED)
                .build();
    }

    @Test(expected = NullPointerException.class)
    public void when_the_car_has_not_color_should_throw_an_exception() {
        Car.builder()
                .plate("234234")
                .build();
    }

    @Test(expected = ParkingException.class)
    public void when_the_car_is_autonomous_should_throw_an_exception() {
        Car car = Car.builder()
                .color(Color.COLORED)
                .plate("123")
                .build();

        parking.park(car);
    }
}
