package br.com.flaviogf.controledeestacionamento;

public class ParkingException extends RuntimeException {
    public ParkingException(String message) {
        super(message);
    }
}
