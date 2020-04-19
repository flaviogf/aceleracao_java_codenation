package br.com.flaviogf.fibonnaci;

import static br.com.flaviogf.fibonnaci.Challenge.isFibonacci;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import static br.com.flaviogf.fibonnaci.Challenge.fibonacci;

public class ChallengeTest {

    @Test
    public void fibonacci_when_has_been_executed_should_return_a_list_with_15_elements() {
        assertEquals(15, fibonacci().size());
    }

    @Test
    public void isFibonacci_when_has_been_executed_with_55_that_is_the_tenth_number_of_fibonnaci_sequence_should_return_true() {
        assertTrue(isFibonacci(55));
    }
}
