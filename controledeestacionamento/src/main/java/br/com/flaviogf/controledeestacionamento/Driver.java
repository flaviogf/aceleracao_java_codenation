package br.com.flaviogf.controledeestacionamento;

public class Driver {
    private final String name;
    private final Integer age;
    private final Integer score;
    private final String license;

    public Driver(String name, Integer age, Integer score, String license) {
        if (name == null) {
            throw new NullPointerException();
        }

        if (license == null) {
            throw new NullPointerException();
        }

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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private Integer age;
        private Integer score;
        private String license;

        public Builder name(String value) {
            this.name = value;
            return this;
        }

        public Builder age(Integer value) {
            if (value < 0) {
                throw new IllegalArgumentException();
            }

            this.age = value;

            return this;
        }

        public Builder score(Integer value) {
            if (value < 0) {
                throw new IllegalArgumentException();
            }

            this.score = value;

            return this;
        }

        public Builder license(String value) {
            this.license = value;
            return this;
        }

        public Driver build() {
            return new Driver(name, age, score, license);
        }
    }
}
