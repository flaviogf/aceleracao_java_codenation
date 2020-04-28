package br.com.flaviogf.mediamodamediana;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatisticTest {
    @Test
    public void average_should_return_the_numbers_average() {
        assertEquals(3, Statistic.average(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void mode_should_return_the_numbers_mode() {
        assertEquals(3, Statistic.mode(new int[]{1, 2, 3, 3}));
    }

    @Test
    public void median_should_return_the_numbers_media() {
        assertEquals(3, Statistic.median(new int[]{1, 2, 3, 3, 4, 5}));
        assertEquals(3, Statistic.median(new int[]{1, 2, 3, 4, 5}));
    }
}
