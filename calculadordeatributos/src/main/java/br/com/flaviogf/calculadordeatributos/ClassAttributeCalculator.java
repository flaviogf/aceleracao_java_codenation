package br.com.flaviogf.calculadordeatributos;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClassAttributeCalculator implements Calculable {
    public BigDecimal sum(Object obj) {
        Class<?> clazz = obj.getClass();

        List<Field> fields = Arrays.stream(clazz.getDeclaredFields())
                .filter(it -> it.isAnnotationPresent(Sum.class) && it.getType().isAssignableFrom(BigDecimal.class))
                .collect(Collectors.toList());

        BigDecimal result = BigDecimal.ZERO;

        for (Field field : fields) {
            try {
                field.setAccessible(true);
                BigDecimal value = (BigDecimal) field.get(obj);
                result = result.add(value);
                field.setAccessible(false);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public BigDecimal sub(Object obj) {
        Class<?> clazz = obj.getClass();

        List<Field> fields = Arrays.stream(clazz.getDeclaredFields())
                .filter(it -> it.isAnnotationPresent(Sub.class) && it.getType().isAssignableFrom(BigDecimal.class))
                .collect(Collectors.toList());

        BigDecimal result = BigDecimal.ZERO;

        for (Field field : fields) {
            try {
                field.setAccessible(true);
                BigDecimal value = (BigDecimal) field.get(obj);
                result = result.add(value);
                field.setAccessible(false);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public BigDecimal totalize(Object obj) {
        return sum(obj).subtract(sub(obj));
    }
}
