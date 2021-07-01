package solar.demo.exceptions;

public class PlanetNotFoundException extends RuntimeException {

    public PlanetNotFoundException(String message) {
        super(message);
    }
}
