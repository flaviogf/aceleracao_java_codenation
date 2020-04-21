package br.com.flaviogf.calculadoradesalarioliquido;

import java.util.Optional;

public class Calculator {

    public long calc(double salary) {
		Tax tax = new INSS(Optional.of(new IRRF(Optional.empty())));

		Long result = Math.round(tax.charge(salary));

		if (result < 1039.00) {
			return 0;
		}

		return result;
    }
}