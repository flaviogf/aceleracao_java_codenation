package br.com.flaviogf.calculadordeatributos;

import java.math.BigDecimal;

public interface Calculable {
    BigDecimal sum(Object obj);
    BigDecimal sub(Object obj);
    BigDecimal totalize(Object obj);
}
