package br.com.flaviogf.calculadoradesalarioliquido;

import java.util.Optional;

public class IRRF extends Tax {

    public IRRF(Optional<Tax> next) {
        super(next);
    }

    @Override
    public Double charge(Double value) {
        if (value <= 3000) {
            return charge(value, 0.0);
        }

        if (value <= 6000) {
            return charge(value, 7.5);
        }

        return charge(value, 15.0);
    }
}