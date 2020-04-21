package br.com.flaviogf.calculadoradesalarioliquido;

import java.util.Optional;

public class INSS extends Tax {

    public INSS(Optional<Tax> next) {
        super(next);
    }

    @Override
    public Double charge(Double value) {
        if (value <= 1500) {
            return charge(value, 8.0);
        }

        if (value <= 4000) {
            return charge(value, 9.0);
        }

        return charge(value, 11.0);
    }
}