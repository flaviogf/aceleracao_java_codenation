package br.com.flaviogf.calculadordeatributos;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ClassAttributeCalculatorTest {
    private ClassAttributeCalculator calculator;

    @Before
    public void setUp() {
        calculator = new ClassAttributeCalculator();
    }

    @Test
    public void sum_when_is_informed_an_object_with_three_fields_annotated_with_sum_and_has_value_ten_should_return_thirty() {
        assertEquals(BigDecimal.valueOf(30), calculator.sum(new Example()));
    }

    @Test
    public void sub_when_is_informed_an_object_with_two_fields_annotated_with_sub_and_has_value_ten_should_return_twenty() {
        assertEquals(BigDecimal.valueOf(20), calculator.sub(new Example()));
    }

    @Test
    public void totalize_when_is_informed_an_object_with_three_fields_annotated_with_sum_and_has_value_ten_and_an_object_with_two_fields_annotated_with_sub_and_has_value_ten_should_return_ten() {
        assertEquals(BigDecimal.valueOf(10), calculator.totalize(new Example()));
    }

    public static class Example {
        @Sum
        private BigDecimal field1 = BigDecimal.TEN;

        @Sum
        private BigDecimal field2 = BigDecimal.TEN;

        @Sum
        private BigDecimal field3 = BigDecimal.TEN;

        @Sub
        private BigDecimal field4 = BigDecimal.TEN;

        @Sub
        private BigDecimal field5 = BigDecimal.TEN;

        private BigDecimal field6 = BigDecimal.TEN;

        private String field7 = "";
    }
}
