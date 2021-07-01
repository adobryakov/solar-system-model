package solar.demo.exceptions;

public class PlanetDataIsIncorrect extends RuntimeException {

    public PlanetDataIsIncorrect(String message) {
        super(message);
    }
}
