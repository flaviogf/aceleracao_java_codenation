package br.com.flaviogf.calculadoradesalarioliquido;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

public class IRRFTest {
    private IRRF tax;

    @Before
    public void setUp() {
        tax = new IRRF(Optional.empty());
    }

    @Test
    public void charge_when_is_executed_with_a_value_lower_than_3000_should_apply_a_discount_of_0_percent() {
        assertEquals(3000.0, tax.charge(3000.0), 0.5);
    }

    @Test
    public void charge_when_is_executed_with_a_value_between_3000_and_6000_should_apply_a_discount_of_7_5_percent() {
        assertEquals(2775.0, tax.charge(3000.1), 0.5);
    }

    @Test
    public void charge_when_is_executed_with_a_value_greater_than_6000_should_apply_a_discount_of_15_percent() {
        assertEquals(5100, tax.charge(6000.1), 0.5);
    }
}