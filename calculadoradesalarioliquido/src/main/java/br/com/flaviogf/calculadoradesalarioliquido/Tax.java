package br.com.flaviogf.calculadoradesalarioliquido;

import java.util.Optional;

public abstract class Tax {
    private Optional<Tax> tax;

    public Tax(Optional<Tax> tax) {
        this.tax = tax;
    }

    public abstract Double charge(Double value);

    protected Double charge(Double value, Double percent) {
        return next(value - value * (percent / 100));
    }

    private Double next(Double value) {
        if (!tax.isPresent()) {
            return value;
        }

        return tax.get().charge(value);
    }
}