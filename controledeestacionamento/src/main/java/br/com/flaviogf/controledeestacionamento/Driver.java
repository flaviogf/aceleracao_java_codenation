package br.com.flaviogf.controledeestacionamento;

public class Driver {
    private final String name;
    private final Integer age;
    private final Integer score;
    private final String license;

    public Driver(String name, Integer age, Integer score, String license) {
        this.name = name;
        this.age = age;
        this.score = score;
        this.license = license;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getScore() {
        return score;
    }

    public String getLicense() {
        return license;
    }
}
