package solar.demo.exceptions;

public class PlanetDoesNotBelongToExpectedSolarSystem extends RuntimeException {

    public PlanetDoesNotBelongToExpectedSolarSystem(String message) {
        super(message);
    }
}
