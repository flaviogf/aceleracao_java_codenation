package br.com.flaviogf.calculadoradesalarioliquido;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

public class INSSTest {
    private INSS tax;

    @Before
    public void setUp() {
        tax = new INSS(Optional.empty());
    }

    @Test
    public void charge_when_is_executed_with_a_value_lower_than_1500_should_apply_a_discount_of_8_percent() {
        assertEquals(920.0, tax.charge(1000.0), 0.5);
    }

    @Test
    public void charge_when_is_executed_with_a_value_between_1500_and_4000_should_apply_a_discount_of_9_percent() {
        assertEquals(1365.0, tax.charge(1500.01), 0.5);
    }

    @Test
    public void charge_when_is_executed_with_a_value_greater_than_4000_should_apply_a_discount_of_14_percent() {
        assertEquals(3560.0, tax.charge(4000.1), 0.5);
    }
}