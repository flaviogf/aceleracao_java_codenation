package br.com.flaviogf.controledeestacionamento;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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

    @Test(expected = ParkingException.class)
    public void when_the_driver_is_under_the_age_should_throw_an_exception() {
        Driver driver = Driver.builder().name("Ada").age(17).score(10).license("1231").build();

        Car car = Car.builder().color(Color.BLACK).plate("123").driver(driver).build();

        parking.park(car);
    }

    @Test(expected = ParkingException.class)
    public void when_the_driver_is_without_score_should_throw_an_exception() {
        Driver driver = Driver.builder().name("Ada").age(25).score(30).license("1231").build();

        Car car = Car.builder().color(Color.WHITE).plate("123").driver(driver).build();

        parking.park(car);
    }

    @Test
    public void should_park() {
        Driver driver = Driver.builder().name("Ada").age(20).score(3).license("1231").build();

        Car car = Car.builder().color(Color.WHITE).plate("123").driver(driver).build();

        parking.park(car);

        assertEquals(1, parking.parkedCars());
    }

    @Test
    public void when_overtaking_the_limit_of_cars_should_not_allow_park_a_new_car() {
        Driver ada = Driver.builder().name("Ada").age(20).score(3).license("1231").build();

        Car whiteCar = Car.builder().color(Color.WHITE).plate("123").driver(ada).build();

        parking.park(whiteCar);

        for (int i = 1; i <= 10; i++) {
            Driver driver = Driver.builder().name(String.format("Driver %d", i))
                    .age(20)
                    .score(3)
                    .license(String.valueOf(i))
                    .build();

            Car car = Car.builder()
                    .color(Color.WHITE)
                    .plate("123")
                    .driver(driver)
                    .build();

            parking.park(car);
        }

        assertEquals(10, parking.parkedCars());
        assertFalse(parking.isParked(whiteCar));
    }

    @Test
    public void when_the_head_of_the_queue_be_a_senior_driver_he_should_not_go_out() {
        Driver ada = Driver.builder().name("Ada").age(60).score(3).license("1231").build();

        Car whiteCar = Car.builder().color(Color.WHITE).plate("123").driver(ada).build();

        parking.park(whiteCar);

        for (int i = 1; i <= 10; i++) {
            Driver driver = Driver.builder().name(String.format("Driver %d", i))
                    .age(20)
                    .score(3)
                    .license(String.valueOf(i))
                    .build();

            Car car = Car.builder().color(Color.WHITE).plate("123").driver(driver).build();

            parking.park(car);
        }

        assertEquals(10, parking.parkedCars());
        assertTrue(parking.isParked(whiteCar));
    }

    @Test(expected = ParkingException.class)
    public void when_all_of_the_drivers_are_senior_should_throw_an_exception() {
        Driver ada = Driver.builder().name("Ada").age(60).score(3).license("1231").build();

        Car whiteCar = Car.builder().color(Color.WHITE).plate("123").driver(ada).build();

        parking.park(whiteCar);

        for (int i = 1; i <= 10; i++) {
            Driver driver = Driver.builder().name(String.format("Driver %d", i))
                    .age(60)
                    .score(3)
                    .license(String.valueOf(i))
                    .build();

            Car car = Car.builder().color(Color.WHITE).plate("123").driver(driver).build();

            parking.park(car);
        }
    }
}
